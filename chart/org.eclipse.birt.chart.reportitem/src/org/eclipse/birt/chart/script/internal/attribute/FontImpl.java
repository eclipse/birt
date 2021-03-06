/*******************************************************************************
 * Copyright (c) 2006 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Actuate Corporation  - initial API and implementation
 *******************************************************************************/

package org.eclipse.birt.chart.script.internal.attribute;

import org.eclipse.birt.chart.model.attribute.FontDefinition;
import org.eclipse.birt.report.model.api.extension.IFont;

/**
 * 
 */

public class FontImpl implements IFont {

	private FontDefinition fd = null;

	public FontImpl(FontDefinition fd) {
		this.fd = fd;
	}

	public String getName() {
		return fd.getName();
	}

	public float getSize() {
		return fd.getSize();
	}

	public boolean isBold() {
		return fd.isBold();
	}

	public boolean isItalic() {
		return fd.isItalic();
	}

	public boolean isStrikeThrough() {
		return fd.isStrikethrough();
	}

	public boolean isUnderline() {
		return fd.isUnderline();
	}

	public void setBold(boolean isBold) {
		fd.setBold(isBold);
	}

	public void setItalic(boolean isItalic) {
		fd.setItalic(isItalic);
	}

	public void setStrikeThrough(boolean isStrikeThrough) {
		fd.setStrikethrough(isStrikeThrough);
	}

	public void setUnderline(boolean isUnderline) {
		fd.setUnderline(isUnderline);
	}

	public void setName(String name) {
		fd.setName(name);
	}

	public void setSize(float size) {
		fd.setSize(size);
	}

}
