package theo.dispatch;

import javax.swing.SwingUtilities;
import theo.view.FilesNameChange;
import theo.view.PhotosSizeChange;
import theo.view.PhotosWaterMarkAdd;

public class ViewDisplay {
	
	public static final String  FILES_NAME_CHANGE="filesNameChange";
	public static final String  PHOTOS_SIZE_CHANGE="photosSizeChange";
	public static final String  PHOTOS_WATERMARK_ADD="photosWaterMarkAdd";
	

	public static void showWin(String win) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				if(win.equals(ViewDisplay.FILES_NAME_CHANGE)){
					FilesNameChange.createAndShowGUI();					
				}else if(win.equals(ViewDisplay.PHOTOS_SIZE_CHANGE)){
					PhotosSizeChange.createAndShowGUI();
				}else if(win.equals(ViewDisplay.PHOTOS_WATERMARK_ADD)){
					PhotosWaterMarkAdd.createAndShowGUI();					
				}
			}
		});
		
	}
}
