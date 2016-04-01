/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package blade.hook.resourcebundle;

import com.liferay.portal.kernel.util.AggregateResourceBundleLoader;
import com.liferay.portal.kernel.util.ResourceBundleLoader;
import com.liferay.portal.kernel.util.ResourceBundleUtil;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.util.ResourceBundle;

/**
 * @author Carlos Sierra Andrés
 */
@Component(
	immediate = true,
	property = {
		"bundle.symbolic.name=com.liferay.blogs.web",
		"resource.bundle.base.name=content.Language",
		"servlet.context.name=blogs-web"
	})
public class ResourceBundleLoaderComponent implements ResourceBundleLoader {

	@Override
	public ResourceBundle loadResourceBundle(String languageId) {
		return _resourceBundleLoader.loadResourceBundle(languageId);
	}

	@Reference(target = "(bundle.symbolic.name=com.liferay.blogs.web)")
	public void setResourceBundleLoader(
		ResourceBundleLoader resourceBundleLoader) {

		_resourceBundleLoader = new AggregateResourceBundleLoader(
			ResourceBundleUtil.getResourceBundleLoader(
				"content.Language",
				ResourceBundleLoaderComponent.class.getClassLoader()),
			resourceBundleLoader);
	}

	private AggregateResourceBundleLoader _resourceBundleLoader;

}
