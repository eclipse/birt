/*******************************************************************************
 * Copyright (c) 2009 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Actuate Corporation  - initial API and implementation
 *******************************************************************************/

package org.eclipse.birt.core.archive.compound;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.eclipse.birt.core.archive.compound.v3.Ext2Entry;
import org.eclipse.birt.core.archive.compound.v3.Ext2File;
import org.eclipse.birt.core.archive.compound.v3.Ext2FileSystem;

public class ArchiveFileV3 implements IArchiveFile {

	public static final String PROPERTY_SYSTEM_ID = "archive.system-id";
	public static final String PROPERTY_DEPEND_ID = "archive.depened-id";

	protected Ext2FileSystem fs;
	protected HashSet<ArchiveEntryV3> openedEntries = new HashSet<>();

	public ArchiveFileV3(String fileName, String mode) throws IOException

	{
		this(fileName, null, mode);
	}

	public ArchiveFileV3(String fileName, RandomAccessFile rf, String mode) throws IOException

	{
		fs = new Ext2FileSystem(fileName, rf, mode);
		if (ArchiveFile.enableFileCache && fs.isRemoveOnExit()) {
			fs.setCacheSize(ArchiveFile.FILE_CACHE_SIZE);
		}
	}

	@Override
	synchronized public void close() throws IOException {
		if (!openedEntries.isEmpty()) {
			ArrayList<ArchiveEntryV3> entries = new ArrayList<>(openedEntries);
			for (ArchiveEntryV3 entry : entries) {
				entry.close();
			}
			openedEntries.clear();
		}
		if (fs != null) {
			fs.close();
			fs = null;
		}
	}

	public void setSystemId(String id) {
		fs.setProperty(PROPERTY_SYSTEM_ID, id);
	}

	public void setDependId(String id) {
		fs.setProperty(PROPERTY_DEPEND_ID, id);
	}

	@Override
	synchronized public ArchiveEntry createEntry(String name) throws IOException {
		Ext2File file = fs.createFile(name);
		return new ArchiveEntryV3(this, file);
	}

	@Override
	public boolean exists(String name) {
		return fs.existFile(name);
	}

	@Override
	synchronized public void flush() throws IOException {
		// first flush all the ext2 files
		for (ArchiveEntryV3 entry : openedEntries) {
			entry.flush();
		}
		fs.flush();
	}

	@Override
	public String getDependId() {
		return fs.getProperty(PROPERTY_DEPEND_ID);
	}

	@Override
	synchronized public ArchiveEntry openEntry(String name) throws IOException {
		if (fs.existFile(name)) {
			Ext2File file = fs.openFile(name);
			return new ArchiveEntryV3(this, file);
		}
		throw new FileNotFoundException(name);
	}

	@Override
	public String getName() {
		return fs.getFileName();
	}

	@Override
	public String getSystemId() {
		return fs.getProperty(PROPERTY_SYSTEM_ID);
	}

	@Override
	public long getUsedCache() {
		return (long) fs.getUsedCacheSize() * 4096;
	}

	@Override
	public List<String> listEntries(String namePattern) {
		Iterable<String> entryNames = namePattern == null ? fs.listAllFiles() : fs.listFiles(namePattern);

		ArrayList<String> files = new ArrayList<>();
		for (String file : entryNames) {
			files.add(file);
		}
		return files;
	}

	@Override
	public synchronized Object lockEntry(String name) throws IOException {
		if (!fs.existFile(name) && !fs.isReadOnly()) {
			Ext2File file = fs.createFile(name);
			file.close();
		}
		Ext2Entry entry = fs.getEntry(name);
		if (entry != null) {
			return entry;
		}
		throw new FileNotFoundException(name);
	}

	@Override
	synchronized public void refresh() throws IOException {
	}

	@Override
	public boolean removeEntry(String name) throws IOException {
		fs.removeFile(name);
		return true;
	}

	@Override
	public void save() throws IOException {
		fs.setRemoveOnExit(false);
		fs.flush();
	}

	@Override
	public void setCacheSize(long cacheSize) {
		long cacheBlock = cacheSize / 4096;
		if (cacheBlock > Integer.MAX_VALUE) {
			fs.setCacheSize(Integer.MAX_VALUE);
		} else {
			fs.setCacheSize((int) cacheBlock);
		}
	}

	@Override
	public long getLength() {
		return fs == null ? 0 : fs.length();
	}

	@Override
	synchronized public void unlockEntry(Object locker) throws IOException {
		assert locker instanceof Ext2Entry;
	}

	synchronized protected void openEntry(ArchiveEntryV3 entry) {
		openedEntries.add(entry);
	}

	synchronized protected void closeEntry(ArchiveEntryV3 entry) {
		openedEntries.remove(entry);
	}
}
