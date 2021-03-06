/**
 * 
 */
package app.xml_content;

import file.class_package.PackageSetter;
import file.class_package.ProdPackageSetter;
import site_mapper.creators.imports.ImportMatcher;
import site_mapper.creators.package_maker.PackageMaker;
import site_mapper.jaxb.pom.Module;
import site_mapper.jaxb.pom.PackageHierarchy;
import site_mapper.jaxb.pom.SiteMapInfo;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public abstract class PomMapper {
	protected XmlContent content;
	protected PackageSetter packageSetter;
	protected PackageHierarchy packageHierarchy;
	protected SiteMapInfo info;
	
	private ImportMatcher impMatcher;
	
	public PomMapper(XmlContent content, ImportMatcher impMatcher) {
		this.content = content;
		this.info = content.getSiteMapInfo();
		this.packageSetter = new ProdPackageSetter();
		this.impMatcher = impMatcher;
	}
		
	public abstract void createPomsFromSource(final String ROOT_DIR, final String XML_SOURCE);
		
	protected void setPackageHierarchy() {
		packageHierarchy = new PackageHierarchy(info);
	}
	protected void makePackage() {
		PackageMaker.makeParentWithPackageInfo(info, packageHierarchy);		
	}
	protected void createPoms(final String XML_SOURCE) {		
		for (Module m : content.getModules()) {
			ModuleMapper.mapModules(m, packageSetter, packageHierarchy, info, impMatcher);
		}					
	}
}
