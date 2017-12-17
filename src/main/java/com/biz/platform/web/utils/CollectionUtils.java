/*******************************************************************************
 * $Header: /cvslv/source/eos/java/ff-core/src/cn/chinaclear/sh/tp/core/util/CollectionUtils.java,v 1.1 2012/07/19 08:17:52 supyuser Exp $
 * $Revision: 1.1 $
 * $Date: 2012/07/19 08:17:52 $
 *
 *==============================================================================
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License. 
 * 
 * Created on Oct 7, 2007
 *******************************************************************************/


package com.biz.platform.web.utils;

import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

public abstract class CollectionUtils {


	public static Map<String, String> toStringMap(String... pairs) {
        Map<String, String> parameters = new HashMap<String, String>();
        if (pairs.length > 0) {
            if (pairs.length % 2 != 0) {
                throw new IllegalArgumentException("pairs must be even.");
            }
            for (int i = 0; i < pairs.length; i = i + 2) {
                parameters.put(pairs[i], pairs[i + 1]);
            }
        }
        return parameters;
    }

	
	/**
	 * 将数组元素全部加到一个集合中。<BR>
	 *
	 * Add all the elements to a collection<BBR>
	 *
	 * @param r_Collection
	 * @param r_Objects
	 */
	public static void addAll(final Collection r_Collection,
			final Object[] r_Objects) {
		if (null == r_Objects) {
			return;
		}

		for (int i = 0; i < r_Objects.length; i++) {
			if (null != r_Objects[i]) {
				r_Collection.add(r_Objects[i]);
			}
		}
	}



	/**
	 * 返回在指定的数组中是否存在指定的类型。<BR>
	 *
	 * Return whether there is a specified type in the arrays.<BR>
	 *
	 * @param r_Objects
	 * @param r_Type
	 * @return
	 */
	public static boolean hasType(Collection r_Objects, Class r_Type) {
		if (null == r_Objects || r_Objects.isEmpty()) {
			return false;
		}

		if (null == r_Type) {
			return false;
		}

		for (Iterator t_Iterator = r_Objects.iterator(); t_Iterator.hasNext();) {
			Object t_Object = t_Iterator.next();
			if (null != t_Object && r_Type == t_Object.getClass()) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Return <code>true</code> if the supplied Collection is <code>null</code>
	 * or empty. Otherwise, return <code>false</code>.
	 * @param collection the Collection to check
	 * @return whether the given Collection is empty
	 */
	public static boolean isEmpty(Collection collection) {
		return (collection == null || collection.isEmpty());
	}

	/**
	 * Return <code>true</code> if the supplied Map is <code>null</code>
	 * or empty. Otherwise, return <code>false</code>.
	 * @param map the Map to check
	 * @return whether the given Map is empty
	 */
	public static boolean isEmpty(Map map) {
		return (map == null || map.isEmpty());
	}




	/**
	 * Merge the given Properties instance into the given Map,
	 * copying all properties (key-value pairs) over.
	 * <p>Uses <code>Properties.propertyNames()</code> to even catch
	 * default properties linked into the original Properties instance.
	 * @param props the Properties instance to merge (may be <code>null</code>)
	 * @param map the target Map to merge the properties into
	 */
	public static void mergePropertiesIntoMap(Properties props, Map map) {
		if (map == null) {
			throw new IllegalArgumentException("Map must not be null");
		}
		if (props != null) {
			for (Enumeration en = props.propertyNames(); en.hasMoreElements();) {
				String key = (String) en.nextElement();
				map.put(key, props.getProperty(key));
			}
		}
	}





	/**
	 * Check whether the given Collection contains the given element instance.
	 * <p>Enforces the given instance to be present, rather than returning
	 * <code>true</code> for an equal element as well.
	 * @param collection the Collection to check
	 * @param element the element to look for
	 * @return <code>true</code> if found, <code>false</code> else
	 */
	public static boolean containsInstance(Collection collection, Object element) {
		if (collection != null) {
			for (Iterator it = collection.iterator(); it.hasNext();) {
				Object candidate = it.next();
				if (candidate == element) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Return <code>true</code> if any element in '<code>candidates</code>' is
	 * contained in '<code>source</code>'; otherwise returns <code>false</code>.
	 * @param source the source Collection
	 * @param candidates the candidates to search for
	 * @return whether any of the candidates has been found
	 */
	public static boolean containsAny(Collection source, Collection candidates) {
		if (isEmpty(source) || isEmpty(candidates)) {
			return false;
		}
		for (Iterator it = candidates.iterator(); it.hasNext();) {
			if (source.contains(it.next())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Return the first element in '<code>candidates</code>' that is contained in
	 * '<code>source</code>'. If no element in '<code>candidates</code>' is present in
	 * '<code>source</code>' returns <code>null</code>. Iteration order is
	 * {@link Collection} implementation specific.
	 * @param source the source Collection
	 * @param candidates the candidates to search for
	 * @return the first present object, or <code>null</code> if not found
	 */
	public static Object findFirstMatch(Collection source, Collection candidates) {
		if (isEmpty(source) || isEmpty(candidates)) {
			return null;
		}
		for (Iterator it = candidates.iterator(); it.hasNext();) {
			Object candidate = it.next();
			if (source.contains(candidate)) {
				return candidate;
			}
		}
		return null;
	}

	/**
	 * Find a value of the given type in the given Collection.
	 * @param collection the Collection to search
	 * @param type the type to look for
	 * @return a value of the given type found, or <code>null</code> if none
	 * @throws IllegalArgumentException if more than one value of the given type found
	 */
	public static Object findValueOfType(Collection collection, Class type) throws IllegalArgumentException {
		if (isEmpty(collection)) {
			return null;
		}
		Class typeToUse = (type != null ? type : Object.class);
		Object value = null;
		for (Iterator it = collection.iterator(); it.hasNext();) {
			Object obj = it.next();
			if (typeToUse.isInstance(obj)) {
				if (value != null) {
					throw new IllegalArgumentException("More than one value of type [" + typeToUse.getName() + "] found");
				}
				value = obj;
			}
		}
		return value;
	}



	/**
	 * Determine whether the given Collection only contains a single unique object.
	 * @param collection the Collection to check
	 * @return <code>true</code> if the collection contains a single reference or
	 * multiple references to the same instance, <code>false</code> else
	 */
	public static boolean hasUniqueObject(Collection collection) {
		if (isEmpty(collection)) {
			return false;
		}
		boolean hasCandidate = false;
		Object candidate = null;
		for (Iterator it = collection.iterator(); it.hasNext();) {
			Object elem = it.next();
			if (!hasCandidate) {
				hasCandidate = true;
				candidate = elem;
			}
			else if (candidate != elem) {
				return false;
			}
		}
		return true;
	}

}