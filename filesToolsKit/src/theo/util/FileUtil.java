package theo.util;

import java.io.File;

public class FileUtil {

	
	
	//返回该文件不带类型的文件名
	public static String getNameWithoutTypeSuffix(File file){
		String name=file.getName();
		int pointDex=name.lastIndexOf(".");
		if(pointDex!=-1)
			return name.substring(0, pointDex-1);
		else
			return name;
	}
	
	//返回该文件的类型
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
	
	//返回符合要求的空文件对象
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
		//文件前缀
		if(prefix!=null&&!"".equals(prefix.trim())){
			path.append(prefix);
		}
		//文件排序数
		if(base>=0){
			path.append("["+base+order+"]");
		}
		//是否保留原文件名
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
