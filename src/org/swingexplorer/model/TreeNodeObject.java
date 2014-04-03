package org.swingexplorer.model;

import java.awt.Component;
import java.lang.ref.WeakReference;

public class TreeNodeObject {
	WeakReference<Component> objRef;
	String name;

	public TreeNodeObject(Component component, String _name) {

		objRef = new WeakReference<Component>(component);
		if (component == null) {
			// this is only for root
			return;
		}
		name = _name;
	}

	public Component getComponent() {
		return (Component) objRef.get();
	}

	public String toString() {
		return name;
	}

	public boolean equals(Object o) {
		if (!(o instanceof TreeNodeObject)) {
			return false;
		}

		TreeNodeObject castedO = (TreeNodeObject) o;
		return castedO.objRef.get() == objRef.get();
	}
}
