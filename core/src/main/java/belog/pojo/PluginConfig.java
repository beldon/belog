package belog.pojo;

import java.util.List;

/**
 * Created by Beldon
 */
public class PluginConfig {

    private List<Element> elements;

    public List<Element> getElements() {
        return elements;
    }

    public void setElements(List<Element> elements) {
        this.elements = elements;
    }

    public static class Element{
        private String type;
        private String key;
        private String value;
        private String label;
        private List<MetaContent> meta;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public List<MetaContent> getMeta() {
            return meta;
        }

        public void setMeta(List<MetaContent> meta) {
            this.meta = meta;
        }
    }


    public static class MetaContent{
        private String text;
        private String value;

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}
