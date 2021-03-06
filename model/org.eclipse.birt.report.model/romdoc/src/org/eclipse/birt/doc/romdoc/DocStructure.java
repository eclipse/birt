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

package org.eclipse.birt.doc.romdoc;

import java.util.Collections;
import java.util.Iterator;

import org.eclipse.birt.report.model.metadata.PropertyDefn;
import org.eclipse.birt.report.model.metadata.StructureDefn;

public class DocStructure extends DocComposite {
	public DocStructure(StructureDefn struct) {
		super(struct);

		Iterator iter = struct.propertiesIterator();
		while (iter.hasNext()) {
			PropertyDefn propDefn = (PropertyDefn) iter.next();
			properties.add(new DocProperty(propDefn));
		}
		Collections.sort(properties, new DocComparator());
	}

	public boolean isElement() {
		return false;
	}

}
