/*******************************************************************************
 * Copyright (c) 2004 Actuate Corporation. All rights reserved. This program and
 * the accompanying materials are made available under the terms of the Eclipse
 * Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Actuate Corporation - initial API and implementation
 ******************************************************************************/

package org.eclipse.birt.report.designer.internal.ui.editors.schematic.editparts;

import org.eclipse.birt.report.designer.core.model.SessionHandleAdapter;
import org.eclipse.birt.report.designer.internal.ui.editors.schematic.figures.LabelFigure;
import org.eclipse.birt.report.designer.internal.ui.editors.schematic.figures.TextFigure;
import org.eclipse.birt.report.designer.internal.ui.util.UIUtil;
import org.eclipse.birt.report.designer.nls.Messages;
import org.eclipse.birt.report.designer.ui.dialogs.TextEditor;
import org.eclipse.birt.report.model.api.CommandStack;
import org.eclipse.birt.report.model.api.TextItemHandle;
import org.eclipse.birt.report.model.api.elements.DesignChoiceConstants;
import org.eclipse.birt.report.model.api.util.StringUtil;
import org.eclipse.draw2d.IFigure;
import org.eclipse.jface.dialogs.Dialog;

/**
 * Text edit part class
 */
public class TextEditPart extends LabelEditPart {

	private static final String FIGURE_DEFAULT_TEXT = Messages.getString("TextEditPart.Figure.Dafault"); //$NON-NLS-1$

	private static final String TEXT_TRANS_MSG = Messages.getString("TextEditPart.trans.editText"); //$NON-NLS-1$

	/**
	 * The constructor.
	 * 
	 * @param model
	 */
	public TextEditPart(Object model) {
		super(model);
	}

	/**
	 * Perform direct edit.
	 */
	public void performDirectEdit() {
		CommandStack stack = SessionHandleAdapter.getInstance().getCommandStack();
		stack.startTrans(TEXT_TRANS_MSG);

		String title = TextEditor.DLG_TITLE_EDIT;

		TextEditor dialog = new TextEditor(UIUtil.getDefaultShell(), title, ((TextItemHandle) getModel()));

		dialog.setEditModal(true);

		if (dialog.open() == Dialog.OK) {
			stack.commit();
			refreshVisuals();
		} else {
			stack.rollback();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#createFigure()
	 */
	protected IFigure createFigure() {
		TextFigure text = new TextFigure();
		return text;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.birt.report.designer.internal.ui.editors.schematic.editparts
	 * .ReportElementEditPart#refreshFigure()
	 */
	public void refreshFigure() {
		super.refreshFigure();

		((LabelFigure) getFigure()).setToolTipText(((TextItemHandle) getModel()).getDisplayContent());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.birt.report.designer.internal.ui.editors.schematic.editparts
	 * .LabelEditPart#getText()
	 */
	protected String getText() {
		TextItemHandle handle = (TextItemHandle) getModel();
		String text = handle.getDisplayContent();
		if (text == null || text.length() == 0) {
			text = FIGURE_DEFAULT_TEXT;
		} else {
			if (text.length() > TRUNCATE_LENGTH
					&& DesignChoiceConstants.TEXT_CONTENT_TYPE_HTML.equals(handle.getContentType())) {
				text = text.substring(0, TRUNCATE_LENGTH - 2) + ELLIPSIS;
			}
		}
		return text;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.birt.report.designer.internal.ui.editors.schematic.editparts
	 * .LabelEditPart#hasText()
	 */
	protected boolean hasText() {
		if (StringUtil.isBlank(((TextItemHandle) getModel()).getDisplayContent())) {
			return false;
		}

		return true;
	}
}