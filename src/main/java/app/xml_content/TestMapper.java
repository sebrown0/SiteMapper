/**
 * 
 */
package app.xml_content;

import app.PomMapperVisitor;
import file.class_package.PackageSetter;
import file.class_package.TestPackageSetter;
import site_mapper.creators.PackageMaker;
import site_mapper.jaxb.pom.Module;
import site_mapper.jaxb.pom.PackageHierarchy;
import site_mapper.jaxb.pom.SiteMapInfo;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public class TestMapper {
	private PomMapperVisitor visitor;
	private XmlContent content;
	private PackageSetter packageSetter = new TestPackageSetter();
	private PackageHierarchy packageHierarchy;
	private SiteMapInfo info;
	
	public TestMapper(PomMapperVisitor visitor, XmlContent content) {	
		this.visitor = visitor;
		this.content = content;
		this.info = content.getSiteMapInfo();
	}
		
	public void createTestPoms(final String XML_SOURCE) {
		if(info != null) {
			info.setXmlSource(XML_SOURCE);
			visitor.setSiteMapInfo(info);
			setPackageHierarchy();
			makePackage();
			createPoms(XML_SOURCE);	
		}else {
			//TODO LOG ERROR
		}
	}
	private void setPackageHierarchy() {
		packageHierarchy = 
				new PackageHierarchy(info.getRootDir(), info.getParentPackage());
	}
	private void makePackage() {
		PackageMaker.makeParentWithPackageInfo(info, packageHierarchy);		
	}
	private void createPoms(final String XML_SOURCE) {		
		for (Module m : content.getModules()) {
			ModuleMapper.mapModules(m, packageSetter, packageHierarchy, info);
		}					
	}
}
