/*******************************************************************************
 * Copyright (c) 2004 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Actuate Corporation  - initial API and implementation
 *******************************************************************************/

package org.eclipse.birt.report.item.crosstab.internal.ui.editors.editparts;

import org.eclipse.birt.report.designer.internal.ui.editors.schematic.editparts.DataEditPart;
import org.eclipse.birt.report.designer.internal.ui.editors.schematic.figures.LabelFigure;
import org.eclipse.birt.report.model.api.DesignElementHandle;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.StackLayout;

/**
 * The date itenm in the level handle editpart.
 */
public class LevelHandleDataItemEditPart extends DataEditPart {

	/**
	 * Constructor
	 * 
	 * @param model
	 */
	public LevelHandleDataItemEditPart(Object model) {
		super(model);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.birt.report.designer.internal.ui.editors.schematic.editparts.
	 * DataEditPart#createFigure()
	 */
	protected IFigure createFigure() {
		LabelFigure label = new LabelFigure();
		label.setLayoutManager(new StackLayout());
		return label;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.birt.report.designer.internal.ui.editors.schematic.editparts.
	 * ReportElementEditPart#refreshBackgroundColor(org.eclipse.birt.report.model.
	 * api.DesignElementHandle)
	 */
	protected void refreshBackgroundColor(DesignElementHandle handle) {
		super.refreshBackgroundColor(handle);

//		Object obj = handle.getProperty( StyleHandle.BACKGROUND_COLOR_PROP );
//
//		if ( obj == null )
//		{
//			getFigure( ).setBackgroundColor( ReportColorConstants.TableGuideFillColor );
//			getFigure( ).setOpaque( true );
//		}
	}

}
