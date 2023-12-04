package experiment_12_4;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class notepad extends JFrame {

    static class FgButton extends JButton {
        public FgButton(Icon icon, String strToolTipText) {
            super(icon);
            setToolTipText(strToolTipText);
        }
    }

    public static Image zoom(String srcPath, double d) {
        BufferedImage src;
        Image image = null;
        try {
            src = ImageIO.read(new File(srcPath));
            int width = src.getWidth(); // 源图宽
            int height = src.getHeight(); // 源图高

            // 获取一个宽、长是原来scale的图像实例
            image = src.getScaledInstance((int) (width * d),
                    (int) (height * d), Image.SCALE_DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    static class FontSelectionFrame extends JFrame {

        private Font selectedFont;
        private final JList<String> fontList;

        public FontSelectionFrame(JFrame parent) {
            super("选择字体");
            setSize(300, 500);
            setLocationRelativeTo(parent);

            String[] fontNames = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
            fontList = new JList<>(fontNames);
            fontList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

            JButton okButton = new JButton("确定");
            JButton cancelButton = new JButton("取消");

            okButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String selectedFontName = fontList.getSelectedValue();
                    if (selectedFontName != null) {
                        selectedFont = new Font(selectedFontName, Font.PLAIN, 12);
                        System.out.println("Selected Font in Dialog: " + selectedFontName);
                    }
                    dispose();
                }
            });

            cancelButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    selectedFont = null;
                    dispose();
                }
            });

            setLayout(new BorderLayout());
            add(new JScrollPane(fontList), BorderLayout.CENTER);

            JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            buttonPanel.add(okButton);
            buttonPanel.add(cancelButton);
            add(buttonPanel, BorderLayout.SOUTH);
        }

        public Font getSelectedFont() {
            return selectedFont;
        }
    }


    public notepad() {
        super("记事本");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 创建菜单
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("文件");
        JMenuItem newItem = new JMenuItem("新建");
        JMenuItem openItem = new JMenuItem("打开");
        JMenuItem saveItem = new JMenuItem("保存");
        JMenuItem fontItem = new JMenuItem("字体");
        JMenuItem exitItem = new JMenuItem("退出");
        fileMenu.add(newItem);
        fileMenu.add(openItem);
        fileMenu.add(saveItem);
        fileMenu.addSeparator();
        fileMenu.add(fontItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);
        menuBar.add(fileMenu);
        setJMenuBar(menuBar);


        //创建工具栏
        Container c = getContentPane();
        JToolBar mtb = new JToolBar();
        c.add(BorderLayout.NORTH, mtb);
        mtb.setLayout(new FlowLayout(FlowLayout.LEFT));
        FgButton[] btn = {
                new FgButton(new ImageIcon(zoom("D:\\IdeaProjects\\Java_course\\new.png",0.12)),
                        "新建文件"),
                new FgButton(new ImageIcon(zoom("D:\\IdeaProjects\\Java_course\\open.png",0.12)),
                        "打开文件"),
                new FgButton(new ImageIcon(zoom("D:\\IdeaProjects\\Java_course\\save.png",0.25)),
                        "保存文件")};

        for (FgButton fgButton : btn) {
            fgButton.setBorder(BorderFactory.createEmptyBorder());
            mtb.add(fgButton);
        }
        mtb.setFloatable(false);



        // 创建文本区域
        JTextArea textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);
        c.add(BorderLayout.CENTER, scrollPane);





        //处理按钮事件
        btn[0].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textArea.setText("");
            }
        });//新建
        btn[1].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fd = new JFileChooser();
                fd.showOpenDialog(null);
                File f = fd.getSelectedFile();
                try {
                    textArea.read(new java.io.FileReader(f), null);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });//打开
        btn[2].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fd=new JFileChooser();
                fd.showOpenDialog(null);
                File f=fd.getSelectedFile();
                    try (PrintWriter writer = new PrintWriter(f)) {
                        writer.write(textArea.getText());
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
        });//保存

        newItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textArea.setText("");
            }
        });//新建
        openItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fd = new JFileChooser();
                fd.showOpenDialog(null);
                File f = fd.getSelectedFile();
                try {
                    textArea.read(new java.io.FileReader(f), null);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });//打开
        saveItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fd=new JFileChooser();
                fd.showOpenDialog(null);
                File f=fd.getSelectedFile();
                try (PrintWriter writer = new PrintWriter(f)) {
                    writer.write(textArea.getText());
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });//保存
        exitItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });//退出
        fontItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                FontSelectionFrame fontFrame = new FontSelectionFrame(notepad.this);
                fontFrame.addWindowListener(new WindowAdapter() {
                    public void windowClosed(WindowEvent e) {
                        Font selectedFont = fontFrame.getSelectedFont();
                        if (selectedFont != null) {
                            System.out.println("Selected Font: " + selectedFont.getFontName() + ", Style: " + selectedFont.getStyle() + ", Size: " + selectedFont.getSize());

                            textArea.setFont(selectedFont);
                        } else {
                            System.out.println("No font selected.");
                        }
                    }
                });
                fontFrame.setVisible(true);
            }
        });//字体



        setVisible(true);
    }

    public static void main(String[] args) {
        new notepad();
    }
}
