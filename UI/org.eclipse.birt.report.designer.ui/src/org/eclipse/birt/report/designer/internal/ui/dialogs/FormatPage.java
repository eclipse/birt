/*******************************************************************************
 * Copyright (c) 2008 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Actuate Corporation  - initial API and implementation
 *******************************************************************************/

package org.eclipse.birt.report.designer.internal.ui.dialogs;

import org.eclipse.swt.widgets.Composite;

import com.ibm.icu.util.ULocale;

/**
 * FormatPage
 */
public abstract class FormatPage extends Composite implements IFormatPage {

	protected FormatLayoutPeer layoutPeer;

	public FormatPage(Composite parent, int style) {
		super(parent, style);
	}

	public void addFormatChangeListener(IFormatChangeListener listener) {
		layoutPeer.addFormatChangeListener(listener);
	}

	public String getCategory() {
		return layoutPeer.getCategory();
	}

	public String getFormatString() {
		return layoutPeer.getFormatString();
	}

	public ULocale getLocale() {
		return layoutPeer.getLocale();
	}

	public String getPattern() {
		return layoutPeer.getPattern();
	}

	public boolean isDirty() {
		return layoutPeer.isDirty();
	}

	public boolean isFormatModified() {
		return layoutPeer.isFormatModified();
	}

	public void setInput(String category, String pattern, ULocale formatLocale) {
		layoutPeer.setInput(category, pattern, formatLocale);
	}

	public void setInput(String formatString) {
		layoutPeer.setInput(formatString);
	}

	public void setPreviewText(String text) {
		layoutPeer.setPreviewText(text);
	}

}
