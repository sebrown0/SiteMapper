package tests.mapping_tests;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PomMapperTests {
//	private static final String ROOT = ".";			
//	
//	private static final String XML_SOURCE = 
//	ROOT + "/src/test/resources/site_map/site_map.xml";
//
//	// This should be the same as ParentPackage in the XML file above.
//	private static final String PARENT_PACKAGE = 
//	ROOT + "/mapped/classes"; 
//
//	private static final String TEST_CLASS_PATH = 
//	PARENT_PACKAGE + "/payroll/left_menu/employees/EmployeeDetails.java";
//	
//	private static final ExistingTestClassFileBuilder FILE_BUILDER = 
//			new ExistingTestClassFileBuilder(
//					"SteveBrown", 
//					"1.0.0", 
//					XML_SOURCE, 
//					"07/01/2020", 
//					"08:53:56");
//	
//	// Set the SiteMapInfo with the test data, i.e. date & time.
//	public class PomTestData implements PomMapperVisitor {
//		@Override
//		public void setSiteMapInfo(SiteMapInfo info) {
//			info
//				.setDate("07/01/2020")
//				.setTime("08:53:56");			
//		}
//	}
//	
//	@BeforeAll
//	static void setup() {
//		 try {
//			deleteExisting();
//		} catch (IOException e) {
//			// Do nothing
//		}
//	}
//	
//	@Test @Order(1)
//	void existingDeleted() throws NoSuchFileException{
//		assertFalse(Files.exists(Paths.get(PARENT_PACKAGE)));
//	}
//		
////	@Test @Order(2)
////	void createPomsFromXML() {		
////		SiteMapContentGetter<PomMapperApp> contentGetter = 
////				new SiteMapContentGetter<>(XML_SOURCE, PomMapperApp.class);
////		XmlContent content = (XmlContent) contentGetter.getContent().get();
////		PomMapperTest mapper = new PomMapperTest((new PomMapperTests()).new PomTestData(), content);
////		mapper.createPomsFromSource(ROOT, XML_SOURCE); //IF THERE ARE PROBLEMS IT COULD BE THE ROOT.
////		
////		//Check the result.
////		ExistingFileScanner scanner = new ExistingFileScanner();
////		scanner.setScanner(TEST_CLASS_PATH);
////		scanner.mapFile();
////		
////		scanner.getClassFile().ifPresentOrElse(
////			cf -> {
////				assertEquals(
////					FILE_BUILDER.CLASS_RESULT_WITH_TEST_METHODS_AND_PACKAGE_RESULT_FOR_MAPPED_CLASSES(),
////					cf.toString());
////			},
////			new Runnable() {					
////				@Override
////				public void run() {
////					fail("Class file is null.");						
////				}
////			});		
////	}
//		
//	@Test @Order(4)
//	void filesCreated() throws NoSuchFileException{
//		assertTrue(Files.exists(Paths.get(PARENT_PACKAGE)));
//	}
//	
//	// HELPERS
//	private static void deleteExisting() throws IOException {
//		Path p = Paths.get(PARENT_PACKAGE);
//		try(Stream<Path> walk = Files.walk(p)){
//			walk
//				.sorted(Comparator.reverseOrder())
//				.forEach(d -> deleteDirectory(d));
//		}
//	}
//	private static void deleteDirectory(Path path) {
//    try {
//        Files.delete(path);
//    } catch (IOException e) {
//        System.err.printf("Unable to delete this path : %s%n%s", path, e);
//    }
//}
}
