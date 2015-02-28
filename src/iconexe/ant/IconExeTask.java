package iconexe.ant;

import iconexe.ant.IconExe.ImageData;
import iconexe.ant.IconExe.ImageLoader;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;

/**
 * Task that adds icons to exe files.
 *
 * @antTaskName IconExe
 */
public class IconExeTask extends Task {

	private File _exe;
	private File _ico;

	public void setExeFile(File exe) {
		_ico = exe;
	}

	public void setIcoFile(File ico) {
		_exe = ico;
	}

	public void execute() throws BuildException {
		try {

			ImageLoader loader = new ImageLoader();

			List<ImageData> images = new ArrayList<ImageData>();
			try {
				// An ICO should contain 7 images, a BMP will contain 1
				ImageData[] current = loader.load(_ico.getAbsolutePath());
				for (int j = 0; j < current.length; j++) {
					images.add(current[j]);
				}
			} catch (RuntimeException e) {
				// ignore so that we process the other images
			}
			ImageData[] data = new ImageData[images.size()];
			data = images.toArray(data);

			int nMissing = IconExe.unloadIcons(_exe.getAbsolutePath(), data);
			if (nMissing != 0)
				System.err.println("Error - " + nMissing
						+ " icon(s) not replaced in " + _ico.getAbsolutePath()
						+ " using " + _exe.getAbsolutePath());

		} catch (Exception e) {
			throw new BuildException(e);
		}
	}

}