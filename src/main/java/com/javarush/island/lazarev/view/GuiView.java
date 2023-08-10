package com.javarush.island.lazarev.view;

import com.javarush.island.lazarev.entities.Nature;
import com.javarush.island.lazarev.entities.TileIcon;
import com.javarush.island.lazarev.location.Location;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.util.List;


public class GuiView implements View {
    private final int tileSize = 60;
    private final Font font = new Font("Segoe UI Emoji", Font.PLAIN, 43);

    private int offsetX = 0;
    private int offsetY = 0;

    private JPanel islandPanel;

    @Override
    public void displayIsland(Location[][] locations) {
        if (islandPanel == null) {
            createPanel(locations);
            JFrame frame = new JFrame("Island");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(new JScrollPane(islandPanel));
            frame.pack();
            frame.setVisible(true);
        }

        islandPanel.repaint();
    }

    private void createPanel(Location[][] locations) {
        islandPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawIsland(g, locations);
            }
        };
        islandPanel.setPreferredSize(new Dimension(locations[0].length * tileSize, locations.length * tileSize));

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
    }

    private void drawIsland(Graphics g, Location[][] locations) {
        for (int i = 0; i < locations.length; i++) {
            for (int j = 0; j < locations[i].length; j++) {
                g.drawRect(j * tileSize, i * tileSize, tileSize, tileSize);
            }
        }

        for (int i = 0; i < locations.length; i++) {
            for (int j = 0; j < locations[i].length; j++) {
                List<Nature> cellContents = locations[i][j].getEntities();
                if (!cellContents.isEmpty()) {
                    Nature lastEntity = cellContents.get(cellContents.size() - 1);
                    TileIcon icon = lastEntity.getIconType();
                    if (icon != null) {
                        String entityIcon = icon.getIcon();

                        FontMetrics fontMetrics = g.getFontMetrics(font);
                        Rectangle2D textBounds = fontMetrics.getStringBounds(entityIcon, g);
                        int textWidth = (int) textBounds.getWidth();
                        int textHeight = (int) textBounds.getHeight();

                        int x = j * tileSize + (tileSize - textWidth) / 2;
                        int y = i * tileSize + (tileSize + textHeight) / 2;

                        g.setFont(font);
                        g.drawString(entityIcon, x, y);
                    }
                }
            }
        }
    }
}