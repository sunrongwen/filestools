package theo.util;

import java.io.File;

public class FileUtil {

	
	
	//���ظ��ļ��������͵��ļ���
	public static String getNameWithoutTypeSuffix(File file){
		String name=file.getName();
		int pointDex=name.lastIndexOf(".");
		if(pointDex!=-1)
			return name.substring(0, pointDex-1);
		else
			return name;
	}
	
	//���ظ��ļ�������
	/**
	 * 
	 * @param file
	 * @return the type name without "."
	 */
	public static String getTypeSuffix(File file){
		String name = file.getName();
		int pointDex = name.lastIndexOf(".");
		return name.substring(pointDex+1);
	}
	
	//���ط���Ҫ��Ŀ��ļ�����
	/**
	 * if base less than 0, then base and order has no effect.
	 * 
	 * @param destinationFolderPath  the address to save the file
	 * @param prefix the first part of  the new file name
	 * @param base a number  
	 * @param order the source's order
	 * @param source file
	 * @param isContainSourceName 
	 * @return
	 */
	public static File createNewFile(String destinationFolderPath,String prefix,int base,int order,File source,boolean isContainSourceName){
		StringBuffer path=new StringBuffer(destinationFolderPath+File.separator);
		//�ļ�ǰ׺
		if(prefix!=null&&!"".equals(prefix.trim())){
			path.append(prefix);
		}
		//�ļ�������
		if(base>=0){
			path.append("["+base+order+"]");
		}
		//�Ƿ���ԭ�ļ���
		if(isContainSourceName){
			path.append(source.getName());
		}else{
			path.append("."+FileUtil.getTypeSuffix(source));
		}
		File f=new File(path.toString());
		return f;		
	}
	
	public static File createNewFile(String destinationFolderPath,File source){
		return new File(destinationFolderPath + File.separatorChar+ source.getName());		
	}
	
}
