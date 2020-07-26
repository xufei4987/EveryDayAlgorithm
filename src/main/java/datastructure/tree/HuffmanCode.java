package datastructure.tree;

import java.io.*;
import java.util.*;

/**
 * 赫夫曼编码：
 * "i like like like java do you like a java"
 * 将字符串拆分为字符以及它出现的次数
 * d:1 y:1 u:1 j:2 v:2 o:2 l:4 k:4 e:4 i:5 a:5 空格:9
 * 按照上述的出现次数作为权值构建一颗huffman树
 * 规定左路径为0，右路径为1
 * 从根节点到字符叶子节点的路径编码即为该字符的赫夫曼编码
 * d:100110 y:100111 u:10010 j:0000 v:0001 o:1000 l:001 k:1110 e:1111 i:101 a:110 空格:01
 * asc码表示该字符串为359bit  赫夫曼编码表示该字符串为133bit 无损压缩
 * ASCII对照表：https://tool.oschina.net/commons?type=4
 *
 * 编码过程：
 * 1、将字符串拆分为字符以及它出现的次数，并按照出现次数作为权值构建一颗huffman树
 * 2、规定左路径为0，右路径为1，从根节点到字符叶子节点的路径编码即为该字符的赫夫曼编码，按照此规则构造编码表
 * 3、将数据按照编码表进行编码，组成一个编码串
 * 4、遍历编码串，8位组成一个byte，最后不足8位也组成一个byte，最后形成的byte[]即为编码后的数据
 * 数据压缩原理：重复次数越多的数据，路径越短，编码表示的bit也就越少，对于重复数据较多的压缩效果比较明显
 *
 * 解码过程：
 * 1、将编码后的byte[]，还原为一个bit串
 * 2、将bit串按照编码表还原为一个byte[]
 * 3、new String(byte[])还原为字符串
 */
public class HuffmanCode {
    public static void main(String[] args) {
//        String content = "i like like like java do you like a java";
//        byte[] bytes = content.getBytes(); //size = 40
//        System.out.println("压缩前字节数为：" + bytes.length);
//        HuffmanCode huffmanCode = new HuffmanCode();
//        Map<Byte, String> huffmanCodeMap = huffmanCode.createHuffmanCodeMap(bytes);
//        //将字符串按照编码表进行编码
//        byte[] huffmanCodeBytes = huffmanCode.huffmanZip(bytes, huffmanCodeMap);
//        System.out.println("压缩后的结果是：");
//        System.out.println(Arrays.toString(huffmanCodeBytes));
//        System.out.println("压缩后的长度是：" + huffmanCodeBytes.length);
//
//        byte[] srcBytes = huffmanCode.unzip(huffmanCodeBytes, huffmanCodeMap);
//        String sourceContent = new String(srcBytes);
//        System.out.println("解压后的内容是：" + sourceContent);
        File srcFile = new File("d://1.bmp");
        File zipFile = new File("d://2.huffmanzip");
        File destFile = new File("d://1_1.bmp");
        HuffmanCode huffmanCode = new HuffmanCode();
//        huffmanCode.zipFile(srcFile,zipFile);
        huffmanCode.unZipFile(zipFile,destFile);
    }

    /**
     * 利用huffman编码压缩文件
     * @param srcFile
     * @param zipFile
     */
    public void zipFile(File srcFile, File zipFile){
        FileInputStream fis = null;
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fis = new FileInputStream(srcFile);
            byte[] bytes = new byte[fis.available()];
            fis.read(bytes);
            Map<Byte, String> huffmanCodeMap = createHuffmanCodeMap(bytes);
            byte[] zipedBytes = huffmanZip(bytes, huffmanCodeMap);
            fos = new FileOutputStream(zipFile);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(zipedBytes);
            oos.writeObject(huffmanCodeMap);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fis.close();
                fos.close();
                oos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    /**
     * 利用huffman编码解压文件
     * @param zipFile
     * @param destFile
     */
    public void unZipFile(File zipFile, File destFile){
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        FileOutputStream fos = null;
        try {
            fis = new FileInputStream(zipFile);
            ois = new ObjectInputStream(fis);
            byte[] bytes = (byte[]) ois.readObject();
            Map<Byte, String> huffmanCodeMap = (Map<Byte, String>) ois.readObject();
            byte[] unzipBytes = unzip(bytes, huffmanCodeMap);
            fos = new FileOutputStream(destFile);
            fos.write(unzipBytes);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                fos.close();
                ois.close();
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public byte[] unzip(byte[] huffmanCodeBytes, Map<Byte, String> huffmanCodeMap) {
        //将huffman编码转换为二进制字符串
        StringBuilder sb = new StringBuilder();
        String tmpstr = null;
        for (int i = 0; i < huffmanCodeBytes.length; i++) {
            if (i == huffmanCodeBytes.length - 1) {
                tmpstr = byteToBitString(huffmanCodeBytes[i], false);
            } else {
                tmpstr = byteToBitString(huffmanCodeBytes[i], true);
            }
            sb.append(tmpstr);
        }
        String bitString = sb.toString();
        //将huffman编码表的kv调换
        HashMap<String, Byte> map = new HashMap<>();
        for (Map.Entry<Byte, String> entry : huffmanCodeMap.entrySet()) {
            map.put(entry.getValue(), entry.getKey());
        }
        List<Byte> srcByteList = new ArrayList<>();

        for (int i = 0; i < bitString.length(); ) {
            int count = 1;
            boolean flag = true;
            Byte b = null;
            while (flag){
                String key = bitString.substring(i, i + count);
                b = map.get(key);
                if(b == null){
                    count ++;
                }else {
                    flag = false;
                }
            }
            srcByteList.add(b);
            i += count;
        }
        //将list转换为array
        byte[] bytes = new byte[srcByteList.size()];
        for (int i = 0; i < srcByteList.size(); i++) {
            bytes[i] = srcByteList.get(i);
        }
        return bytes;
    }

    /**
     * 将字节转换为bit字符串
     *
     * @param b    输入的字节
     * @param flag 是否需要补齐高位
     * @return
     */
    private String byteToBitString(byte b, boolean flag) {
        //转换为int
        int tmp = b;
        //补齐高位
        if (flag) {
            tmp = tmp | 0x100;
        }
        String str = Integer.toBinaryString(tmp);
        if (flag) {
            return str.substring(str.length() - 8);
        } else {
            return str;
        }

    }

    /**
     * 通过byte数组数据构建huffman编码表
     * @param bytes
     * @return
     */
    public Map<Byte, String> createHuffmanCodeMap(byte[] bytes) {
        //将bytes转换为Node数组
        List<Node> nodes = getNodes(bytes);
        //将node数组构建为huffman树
        Node rootNode = buildHuffmanTree(nodes);
        //将huffman树转换为huffman编码表
        Map<Byte, String> huffmanCodesMap = getHuffmanCodes(rootNode);
        return huffmanCodesMap;
    }

    /**
     * @param bytes          需要压缩的字节数组
     * @param huffmanCodeMap
     * @return 压缩后的字节数组
     */
    public byte[] huffmanZip(byte[] bytes, Map<Byte, String> huffmanCodeMap) {
        //将字符串按照编码表进行编码
        byte[] huffmanCodeBytes = zip(bytes, huffmanCodeMap); //size = 17
        return huffmanCodeBytes;
    }

    /**
     * 将字符串按照编码表进行编码
     *
     * @param bytes           字符串数组
     * @param huffmanCodesMap 赫夫曼编码表
     * @return
     */
    private byte[] zip(byte[] bytes, Map<Byte, String> huffmanCodesMap) {
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : bytes) {
            stringBuilder.append(huffmanCodesMap.get(b));
        }
        //计算需要占用的字节数组的大小
        int size;
        if (stringBuilder.length() % 8 == 0) {
            size = stringBuilder.length() / 8;
        } else {
            size = stringBuilder.length() / 8 + 1;
        }
        byte[] huffmanCodesBytes = new byte[size];
        for (int i = 0, n = 0; i < stringBuilder.length(); i += 8, n++) {
            if (i + 8 > stringBuilder.length()) {
                huffmanCodesBytes[n] = (byte) Integer.parseInt(stringBuilder.substring(i), 2);
            } else {
                huffmanCodesBytes[n] = (byte) Integer.parseInt(stringBuilder.substring(i, i + 8), 2);
            }
        }
        return huffmanCodesBytes;
    }

    private Map<Byte, String> getHuffmanCodes(Node rootNode) {
        HashMap<Byte, String> huffmanCodesMap = new HashMap<>();
        StringBuilder stringBuilder = new StringBuilder();
        getCodes(rootNode, "", huffmanCodesMap, stringBuilder);
        return huffmanCodesMap;
    }

    private void getCodes(Node node, String code, HashMap<Byte, String> huffmanCodesMap, StringBuilder stringBuilder) {
        if (node == null) {
            return;
        }
        StringBuilder tmp = new StringBuilder(stringBuilder);
        tmp.append(code);
        if (node.data != null) {
            huffmanCodesMap.put(node.data, tmp.toString());
            return;
        }
        getCodes(node.left, "0", huffmanCodesMap, tmp);
        getCodes(node.right, "1", huffmanCodesMap, tmp);
    }

    private void preorder(Node rootNode) {
        System.out.println(rootNode);
        if (rootNode.left != null) {
            preorder(rootNode.left);
        }
        if (rootNode.right != null) {
            preorder(rootNode.right);
        }
    }

    private Node buildHuffmanTree(List<Node> nodes) {
        while (nodes.size() > 1) {
            Collections.sort(nodes);
            Node left = nodes.get(0);
            Node right = nodes.get(1);
            Node node = new Node(null, left.weight + right.weight);
            node.left = left;
            node.right = right;
            nodes.remove(left);
            nodes.remove(right);
            nodes.add(node);
        }
        return nodes.get(0);
    }

    private List<Node> getNodes(byte[] bytes) {
        ArrayList<Node> nodes = new ArrayList<>();
        HashMap<Byte, Integer> map = new HashMap<>();
        for (byte b : bytes) {
            Integer count = map.get(b);
            if (count == null) {
                map.put(b, 1);
            } else {
                map.put(b, count + 1);
            }
        }
        map.entrySet().forEach(entry -> {
            nodes.add(new Node(entry.getKey(), entry.getValue()));
        });
        return nodes;
    }

    private static class Node implements Comparable<Node> {
        private Byte data; //字符对应的asc码
        private int weight; //权值
        private Node left;
        private Node right;

        public Node(Byte data, int weight) {
            this.data = data;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return this.weight - o.weight;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "data=" + data +
                    ", weight=" + weight +
                    '}';
        }
    }
}
