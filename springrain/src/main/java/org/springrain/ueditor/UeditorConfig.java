package org.springrain.ueditor;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.ArrayUtils;

public class UeditorConfig implements java.io.Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	

    public static final String ACTION_UPLOAD_IMAGE = "uploadimg";
    public static final String ACTION_UPLOAD_SCRAWL = "uploadscrawl";
    public static final String ACTION_UPLOAD_VIDEO = "uploadvideo";
    public static final String ACTION_UPLOAD_FILE = "uploadfile";
    public static final String ACTION_CATCHIMAGE = "catchimage";
    public static final String ACTION_LISTFILE = "listfile";
    public static final String ACTION_LISTIMAGE = "listimage";

    public static final String FIELD_NAME = "file";
    public static final String SCRAWL_TYPE = ".jpg";

    private static final String[] IMAGE_ALLOW_FILES = new String[] { ".png", ".jpg", ".jpeg", ".gif", ".bmp" };

    private static final String[] VIDEO_ALLOW_FILES = new String[] { ".flv", ".swf", ".mkv", ".avi", ".rm", ".rmvb", ".mpeg",
            ".mpg", ".ogg", ".ogv", ".mov", ".wmv", ".mp4", ".webm", ".mp3", ".wav", ".mid" };
    private static final String[] ALLOW_FILES = ArrayUtils.addAll(ArrayUtils.addAll(VIDEO_ALLOW_FILES, IMAGE_ALLOW_FILES),
            new String[] { ".rar", ".zip", ".tar", ".gz", ".7z", ".bz2", ".cab", ".iso", ".doc", ".docx", ".xls", ".xlsx", ".ppt",
                    ".pptx", ".pdf", ".txt", ".md", ".xml" });
    private static final Map<String, String> CONTENT_TYPE_MAP = new HashMap<String, String>() {
        private static final long serialVersionUID = 1L;
        {
            put("image/gif", ".gif");
            put("image/jpeg", ".jpg");
            put("image/jpg", ".jpg");
            put("image/png", ".png");
            put("image/bmp", ".bmp");
        }
    };
    
   public UeditorConfig(){
	   
   }
   
   
   public UeditorConfig(String urlPrefix){
	   
	   
	   
	   this.setImageActionName(ACTION_UPLOAD_IMAGE);
       this.setSnapscreenActionName(ACTION_UPLOAD_IMAGE);
       this.setScrawlActionName(ACTION_UPLOAD_SCRAWL);
       this.setVideoActionName(ACTION_UPLOAD_VIDEO);
       this.setFileActionName(ACTION_UPLOAD_FILE);
       this.setCatcherActionName(ACTION_CATCHIMAGE);
       this.setImageManagerActionName(ACTION_LISTIMAGE);
       this.setFileManagerActionName(ACTION_LISTFILE);
       this.setImageFieldName(FIELD_NAME);
       this.setScrawlFieldName(FIELD_NAME);
       this.setCatcherFieldName(FIELD_NAME);
       this.setVideoFieldName(FIELD_NAME);
       this.setFileFieldName(FIELD_NAME);

       this.setImageAllowFiles(IMAGE_ALLOW_FILES);
       this.setCatcherAllowFiles(IMAGE_ALLOW_FILES);
       this.setVideoAllowFiles(VIDEO_ALLOW_FILES);
       this.setFileAllowFiles(ALLOW_FILES);
       this.setImageManagerAllowFiles(IMAGE_ALLOW_FILES);
       this.setFileManagerAllowFiles(ALLOW_FILES);	   
	   
	   this.setImageUrlPrefix(urlPrefix+"image/");
       this.setScrawlUrlPrefix(urlPrefix+"scrawl/");
       this.setSnapscreenUrlPrefix(urlPrefix+"image/");
       this.setCatcherUrlPrefix(urlPrefix+"image/");
       this.setVideoUrlPrefix(urlPrefix+"video/");
       this.setFileUrlPrefix(urlPrefix+"file/");
       this.setImageManagerUrlPrefix(urlPrefix+"image/");
       this.setFileManagerUrlPrefix(urlPrefix+"file/");
	   
   }
   
   
   
   
    
    
    
    private String imageActionName;
    private String snapscreenActionName;
    private String scrawlActionName;
    private String videoActionName;
    private String fileActionName;
    private String catcherActionName;
    private String imageManagerActionName;
    private String fileManagerActionName;
    private String imageFieldName;
    private String scrawlFieldName;
    private String catcherFieldName;
    private String videoFieldName;
    private String fileFieldName;
    private String imageUrlPrefix;
    private String scrawlUrlPrefix;
    private String snapscreenUrlPrefix;
    private String catcherUrlPrefix;
    private String videoUrlPrefix;
    private String fileUrlPrefix;
    private String imageManagerUrlPrefix;
    private String fileManagerUrlPrefix;
    private String[] imageAllowFiles;
    private String[] catcherAllowFiles;
    private String[] videoAllowFiles;
    private String[] fileAllowFiles;
    private String[] imageManagerAllowFiles;
    private String[] fileManagerAllowFiles;

    public String getImageActionName() {
        return imageActionName;
    }

    public void setImageActionName(String imageActionName) {
        this.imageActionName = imageActionName;
    }

    public String getSnapscreenActionName() {
        return snapscreenActionName;
    }

    public void setSnapscreenActionName(String snapscreenActionName) {
        this.snapscreenActionName = snapscreenActionName;
    }

    public String getScrawlActionName() {
        return scrawlActionName;
    }

    public void setScrawlActionName(String scrawlActionName) {
        this.scrawlActionName = scrawlActionName;
    }

    public String getVideoActionName() {
        return videoActionName;
    }

    public void setVideoActionName(String videoActionName) {
        this.videoActionName = videoActionName;
    }

    public String getFileActionName() {
        return fileActionName;
    }

    public void setFileActionName(String fileActionName) {
        this.fileActionName = fileActionName;
    }

    public String getCatcherActionName() {
        return catcherActionName;
    }

    public void setCatcherActionName(String catcherActionName) {
        this.catcherActionName = catcherActionName;
    }

    public String getImageManagerActionName() {
        return imageManagerActionName;
    }

    public void setImageManagerActionName(String imageManagerActionName) {
        this.imageManagerActionName = imageManagerActionName;
    }

    public String getFileManagerActionName() {
        return fileManagerActionName;
    }

    public void setFileManagerActionName(String fileManagerActionName) {
        this.fileManagerActionName = fileManagerActionName;
    }

    public String getImageFieldName() {
        return imageFieldName;
    }

    public void setImageFieldName(String imageFieldName) {
        this.imageFieldName = imageFieldName;
    }

    public String getScrawlFieldName() {
        return scrawlFieldName;
    }

    public void setScrawlFieldName(String scrawlFieldName) {
        this.scrawlFieldName = scrawlFieldName;
    }

    public String getCatcherFieldName() {
        return catcherFieldName;
    }

    public void setCatcherFieldName(String catcherFieldName) {
        this.catcherFieldName = catcherFieldName;
    }

    public String getVideoFieldName() {
        return videoFieldName;
    }

    public void setVideoFieldName(String videoFieldName) {
        this.videoFieldName = videoFieldName;
    }

    public String getFileFieldName() {
        return fileFieldName;
    }

    public void setFileFieldName(String fileFieldName) {
        this.fileFieldName = fileFieldName;
    }

    public String getImageUrlPrefix() {
        return imageUrlPrefix;
    }

    public void setImageUrlPrefix(String imageUrlPrefix) {
        this.imageUrlPrefix = imageUrlPrefix;
    }

    public String getScrawlUrlPrefix() {
        return scrawlUrlPrefix;
    }

    public void setScrawlUrlPrefix(String scrawlUrlPrefix) {
        this.scrawlUrlPrefix = scrawlUrlPrefix;
    }

    public String getSnapscreenUrlPrefix() {
        return snapscreenUrlPrefix;
    }

    public void setSnapscreenUrlPrefix(String snapscreenUrlPrefix) {
        this.snapscreenUrlPrefix = snapscreenUrlPrefix;
    }

    public String getCatcherUrlPrefix() {
        return catcherUrlPrefix;
    }

    public void setCatcherUrlPrefix(String catcherUrlPrefix) {
        this.catcherUrlPrefix = catcherUrlPrefix;
    }

    public String getVideoUrlPrefix() {
        return videoUrlPrefix;
    }

    public void setVideoUrlPrefix(String videoUrlPrefix) {
        this.videoUrlPrefix = videoUrlPrefix;
    }

    public String getFileUrlPrefix() {
        return fileUrlPrefix;
    }

    public void setFileUrlPrefix(String fileUrlPrefix) {
        this.fileUrlPrefix = fileUrlPrefix;
    }

    public String getImageManagerUrlPrefix() {
        return imageManagerUrlPrefix;
    }

    public void setImageManagerUrlPrefix(String imageManagerUrlPrefix) {
        this.imageManagerUrlPrefix = imageManagerUrlPrefix;
    }

    public String getFileManagerUrlPrefix() {
        return fileManagerUrlPrefix;
    }

    public void setFileManagerUrlPrefix(String fileManagerUrlPrefix) {
        this.fileManagerUrlPrefix = fileManagerUrlPrefix;
    }

    public String[] getImageAllowFiles() {
        return imageAllowFiles;
    }

    public void setImageAllowFiles(String[] imageAllowFiles) {
        this.imageAllowFiles = imageAllowFiles;
    }

    public String[] getCatcherAllowFiles() {
        return catcherAllowFiles;
    }

    public void setCatcherAllowFiles(String[] catcherAllowFiles) {
        this.catcherAllowFiles = catcherAllowFiles;
    }

    public String[] getVideoAllowFiles() {
        return videoAllowFiles;
    }

    public void setVideoAllowFiles(String[] videoAllowFiles) {
        this.videoAllowFiles = videoAllowFiles;
    }

    public String[] getFileAllowFiles() {
        return fileAllowFiles;
    }

    public void setFileAllowFiles(String[] fileAllowFiles) {
        this.fileAllowFiles = fileAllowFiles;
    }

    public String[] getImageManagerAllowFiles() {
        return imageManagerAllowFiles;
    }

    public void setImageManagerAllowFiles(String[] imageManagerAllowFiles) {
        this.imageManagerAllowFiles = imageManagerAllowFiles;
    }

    public String[] getFileManagerAllowFiles() {
        return fileManagerAllowFiles;
    }

    public void setFileManagerAllowFiles(String[] fileManagerAllowFiles) {
        this.fileManagerAllowFiles = fileManagerAllowFiles;
    }
}
