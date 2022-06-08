import java.nio.Buffer;

// https://leetcode.cn/problems/design-a-text-editor/
class TextEditor {
    public StringBuffer buffer;
    public int cursor;

    public TextEditor() {
        buffer = new StringBuffer();
        cursor = 0;
    }

    public void addText(String text) {
        buffer.insert(cursor, text);
        cursor += text.length();
    }

    public int deleteText(int k) {
        int deleteCount = Math.min(k, cursor);
        buffer.delete(cursor - deleteCount, cursor);
        cursor = cursor - deleteCount;
        return deleteCount;
    }

    public String cursorLeft(int k) {
        cursor = Math.max(0, cursor - k);
        return buffer.substring(Math.max(0, cursor - 10), cursor);
    }

    public String cursorRight(int k) {
        cursor = Math.min(buffer.length(), cursor+k);
        return buffer.substring(Math.max(0, cursor - 10), cursor);
    }
}

/**
 * Your TextEditor object will be instantiated and called as such:
 * TextEditor obj = new TextEditor();
 * obj.addText(text);
 * int param_2 = obj.deleteText(k);
 * String param_3 = obj.cursorLeft(k);
 * String param_4 = obj.cursorRight(k);
 */