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

package org.eclipse.birt.report.item.crosstab.core.re.executor;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.birt.report.engine.content.IBandContent;
import org.eclipse.birt.report.engine.content.IContent;
import org.eclipse.birt.report.engine.content.ITableBandContent;
import org.eclipse.birt.report.engine.extension.IReportItemExecutor;

/**
 * CrosstabMeasureExecutor
 */
public class CrosstabMeasureExecutor extends BaseCrosstabExecutor {

	private int currentElement;
	private List elements;

	public CrosstabMeasureExecutor(BaseCrosstabExecutor parent) {
		super(parent);
	}

	public void close() {
		super.close();

		elements = null;
	}

	public IContent execute() {
		ITableBandContent content = context.getReportContent().createTableBandContent();
		content.setBandType(IBandContent.BAND_DETAIL);

		initializeContent(content, null);

		prepareChildren();

		return content;
	}

	private void prepareChildren() {
		elements = new ArrayList();
		currentElement = 0;

		int count = crosstabItem.getMeasureCount();
		int totalRow = (count > 1 && MEASURE_DIRECTION_VERTICAL.equals(crosstabItem.getMeasureDirection())) ? count
				: Math.min(count, 1);

		for (int i = 0; i < totalRow; i++) {
			elements.add(new CrosstabMeasureRowExecutor(this, i));
		}
	}

	public IReportItemExecutor getNextChild() {
		return (IReportItemExecutor) elements.get(currentElement++);
	}

	public boolean hasNextChild() {
		if (currentElement < elements.size()) {
			return true;
		}

		return false;
	}

}
