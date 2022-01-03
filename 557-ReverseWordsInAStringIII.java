// 使用额外储存空间
/* 
对于java而言，String是不可变字符数组，所以无法避免空间的开销.
String底层使用的数据结构是 final修饰的字符数组
众所周知，final修饰的是常量，所以无法更改
但是对于String Buffer 或者 String Builder而言（这里举一个例子就好，因为他们都继承了AbstractStringBuilder）
底层数据结构仅仅是char[] value，只是一个char[]数组，所以可以进行改变.
再来说说为什么String不可变的原因：
1. 字符串常量池的需要
- 字符串常量池是Java堆内存中一个特殊的存储区域, 当创建一个String对象时,假如此字符串值已经存在于常量池中,则不会创建一个新的对象,而是引用已经存在的对象。（所谓的节省空间）
2. 安全性
- String被许多的Java类(库)用来当做参数,比如 网络连接地址URL,文件路径path, 假若String不是固定不变的,将会引起各种安全隐患。
3. String对象的 hashcode 问题
- 字符串不变性保证了hash码的唯一性,因此可以放心地进行缓存.这也是一种性能优化手段,意味着不必每次都去计算新的哈希码
*/

class Solution {
    public String reverseWords(String s) {
        StringBuffer ans = new StringBuffer();
        int len = s.length();
        int index = 0;
        while (index < len) {
            int start = index;
            while (index < len && s.charAt(index) != ' ')
                index++;
            int end = index - 1;
            for (int j = 0; j < end - start + 1; j++) {
                ans.append(s.charAt(end - j));
            }
            while (index < len && s.charAt(index) == ' ') {
                index++;
                ans.append(' ');
            }
        }
        return ans.toString();
    }
}