package iconexe;

import iconexe.ant.IconExeTask;

import java.io.File;

import org.apache.tools.ant.BuildLogger;
import org.apache.tools.ant.DefaultLogger;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.ProjectHelper;
import org.junit.Test;

public class IconExeTaskTest extends TestBase {

	
    private Project project;

	@Test
	public void testRunTestBuildFile() throws Exception {
	    runBuildFileTarget(new File( getFileResource( "/../../build/build.xml")),"test2");
	}
	
	protected void runBuildFileTarget( File buildFile , String target) {
	    BuildLogger logger = new DefaultLogger();
	    logger.setOutputPrintStream(System.out);
	    logger.setErrorPrintStream(System.out);
	    logger.setMessageOutputLevel(Project.MSG_INFO);

	    project = new Project();
	    project.addBuildListener(logger);
        project.setCoreLoader(getClass().getClassLoader());
        project.init();
        project.setBaseDir(buildFile.getParentFile());

        ProjectHelper.configureProject(project, buildFile);
        String targetToExecute = (target != null && target.trim().length() > 0) ? target.trim() : project.getDefaultTarget();
        project.executeTarget(targetToExecute);
        project.fireBuildFinished(null);
        
	}
	
	protected IconExeTask createBasicTaskWithFiles( File dir ) {
	    IconExeTask task = new IconExeTask();
	    task.setProject( createProject() );
	    
	    task.setExeFile(dir);
        task.setIcoFile(dir);

	    return task;
	}
	
	private Project createProject() {
		Project project = new Project();
		project.setCoreLoader(getClass().getClassLoader());
		project.init();
		return project;
	}

}
