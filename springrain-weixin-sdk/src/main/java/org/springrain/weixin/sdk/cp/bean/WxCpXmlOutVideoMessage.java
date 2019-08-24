package org.springrain.weixin.sdk.cp.bean;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import org.springrain.weixin.sdk.common.WxConsts;
import org.springrain.weixin.sdk.common.util.xml.XStreamCDataConverter;

@XStreamAlias("xml")
public class WxCpXmlOutVideoMessage extends WxCpXmlOutMessage {
    private static final long serialVersionUID = -8672761162722733622L;

    @XStreamAlias("Video")
    protected final Video video = new Video();

    public WxCpXmlOutVideoMessage() {
        this.msgType = WxConsts.XML_MSG_VIDEO;
    }

    public String getMediaId() {
        return this.video.getMediaId();
    }

    public void setMediaId(String mediaId) {
        this.video.setMediaId(mediaId);
    }

    public String getTitle() {
        return this.video.getTitle();
    }

    public void setTitle(String title) {
        this.video.setTitle(title);
    }

    public String getDescription() {
        return this.video.getDescription();
    }

    public void setDescription(String description) {
        this.video.setDescription(description);
    }


    @XStreamAlias("Video")
    public static class Video {

        @XStreamAlias("MediaId")
        @XStreamConverter(value = XStreamCDataConverter.class)
        private String mediaId;

        @XStreamAlias("Title")
        @XStreamConverter(value = XStreamCDataConverter.class)
        private String title;

        @XStreamAlias("Description")
        @XStreamConverter(value = XStreamCDataConverter.class)
        private String description;

        public String getMediaId() {
            return this.mediaId;
        }

        public void setMediaId(String mediaId) {
            this.mediaId = mediaId;
        }

        public String getTitle() {
            return this.title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return this.description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

    }

}
