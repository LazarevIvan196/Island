package com.javarush.island.lazarev.view;

import com.javarush.island.lazarev.entities.Nature;
import com.javarush.island.lazarev.entities.TileIcon;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.util.List;

public class GuiView implements View {
    private final int tileSize = 50;
    private final Font font = new Font("Segoe UI Emoji", Font.PLAIN, 20);


    private int offsetX = 0;
    private int offsetY = 0;

    @Override
    public void displayIsland(List<Nature>[][] grid) {
        JFrame frame = new JFrame("Island");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(grid[0].length * tileSize, grid.length * tileSize));

        JPanel islandPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawIsland(g, grid);
            }
        };
        islandPanel.setPreferredSize(new Dimension(grid[0].length * tileSize, grid.length * tileSize));

        islandPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                offsetX = e.getX();
                offsetY = e.getY();
            }
        });

        islandPanel.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                int dx = e.getX() - offsetX;
                int dy = e.getY() - offsetY;
                offsetX = e.getX();
                offsetY = e.getY();

                JViewport viewport = (JViewport) SwingUtilities.getAncestorOfClass(JViewport.class, islandPanel);
                if (viewport != null) {
                    Point viewPosition = viewport.getViewPosition();
                    viewPosition.translate(-dx, -dy);
                    islandPanel.scrollRectToVisible(new Rectangle(viewPosition, viewport.getSize()));
                }
            }
        });

        frame.add(new JScrollPane(islandPanel));
        frame.pack();
        frame.setVisible(true);
    }

    private void drawIsland(Graphics g, List<Nature>[][] grid) {

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                g.drawRect(j * tileSize, i * tileSize, tileSize, tileSize);
            }
        }

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                List<Nature> cellContents = grid[i][j];
                int totalEntities = cellContents.size();
                if (totalEntities > 0) {
                    Nature lastEntity = cellContents.get(totalEntities - 1);
                    TileIcon icon = lastEntity.getIconType();
                    if (icon != null) {
                        String entityInfo = String.format("%s %d", icon.getIcon(), totalEntities);
                        // Вычисляем ширину и высоту текста
                        FontMetrics fontMetrics = g.getFontMetrics(font);
                        Rectangle2D textBounds = fontMetrics.getStringBounds(entityInfo, g);
                        int textWidth = (int) textBounds.getWidth();
                        int textHeight = (int) textBounds.getHeight();


                        int x = j * tileSize + (tileSize - textWidth) / 2;
                        int y = i * tileSize + (tileSize + textHeight) / 2;

                        g.setFont(font);
                        g.drawString(entityInfo, x, y);
                    }
                }
            }
        }
    }
}