
/*******************************************************************************
 * Copyright (c) 2004, 2007 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Actuate Corporation  - initial API and implementation
 *******************************************************************************/
package org.eclipse.birt.data.engine.olap.data.api;

import org.eclipse.birt.data.engine.core.DataException;
import org.eclipse.birt.data.engine.olap.util.filter.IFacttableRow;

/**
 * 
 */

public interface IComputedMeasureHelper {
	/**
	 * 
	 * @return
	 */
	public MeasureInfo[] getAllComputedMeasureInfos();

	/**
	 * 
	 * @return
	 */
	public Object[] computeMeasureValues(IFacttableRow factTableRow) throws DataException;

	/**
	 * 
	 * @throws DataException
	 */
	public void cleanUp() throws DataException;
}
