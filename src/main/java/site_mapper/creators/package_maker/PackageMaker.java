/**
 * 
 */
package site_mapper.creators.package_maker;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

import org.apache.logging.log4j.LogManager;

import site_mapper.creators.comment.Comments;
import site_mapper.jaxb.pom.PackageHierarchy;
import site_mapper.jaxb.pom.SiteMapInfo;
import utils.text_utils.StringUtils;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 * 
 * Create specified package(s), starting at the given root.
 * If the package already exists it will not be overwritten.
 * 
 */
public class PackageMaker {
	private static String filePath;
	private static String packagePath;	
	
	public static void makeParentWithPackageInfo(SiteMapInfo siteMap, PackageHierarchy ph) {
		filePath = ph.getRoot() + "/" + ph.getHierarchyFwdSlashNotation();
		packagePath = ph.getHierarchyDotNotation();
		
		makePackages();
		createPackageInfo(siteMap, Comments.getParentPackageComments(siteMap));
	}
	
	public static void makeWithPackageInfo(SiteMapInfo siteMap, PackageHierarchy ph) {
		filePath = ph.getRoot() + "/" + ph.getHierarchyFwdSlashNotation();
		packagePath = ph.getHierarchyDotNotation();

		makePackages();
		createPackageInfo(siteMap, Comments.getPackageComments(siteMap));
	}
	
	private static void makePackages() {
		LogManager.getLogger(
				PackageMaker.class).info("Creating dir [" + filePath + "] if not present");
		new File(filePath).mkdirs();
	}
	
	private static void createPackageInfo(SiteMapInfo siteMap, String comments) {		
		try (Writer writer = 
				new BufferedWriter(
						new OutputStreamWriter(
								new FileOutputStream(filePath + "/package-info.java"), StandardCharsets.UTF_8))) {
			writer.write(comments);
	    writer.write("package " + StringUtils.replaceFwdSlashes(packagePath, ".") + ";");
		} 
		catch (IOException ex) {
		    // TODO - Handle me
		}  
	}

}
