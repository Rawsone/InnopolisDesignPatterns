package observer.pattern;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Vector;

import javax.swing.JPanel;

import observer.CourseRecord;
import observer.LayoutConstants;

/**
 * This class represents a bar chart view of a vector of data. Uses the Observer
 * pattern.
 */
@SuppressWarnings("serial")
public class BarChartObserver extends JPanel implements Observer {

    private Vector<CourseRecord> courseData;

    /**
     * Creates a BarChartObserver object
     *
     * @param data a CourseData object to observe
     */
    public BarChartObserver(CourseData data) {
        data.attach(this, false, true);
        this.courseData = data.getUpdate();
        this.setPreferredSize(new Dimension(2 * LayoutConstants.xOffset
                + (LayoutConstants.barSpacing + LayoutConstants.barWidth)
                * this.courseData.size(), LayoutConstants.graphHeight + 2
                * LayoutConstants.yOffset));
        this.setBackground(Color.white);
    }

    /**
     * Paint method
     *
     * @param g a Graphics object on which to paint
     */
    public void paint(Graphics g) {
        super.paint(g);
        LayoutConstants.paintBarChartOutline(g, this.courseData.size());
        for (int i = 0; i < courseData.size(); i++) {
            CourseRecord record = (CourseRecord) courseData.elementAt(i);
            g.setColor(Color.blue);
            g.fillRect(
                    LayoutConstants.xOffset + (i + 1)
                            * LayoutConstants.barSpacing + i
                            * LayoutConstants.barWidth, LayoutConstants.yOffset
                            + LayoutConstants.graphHeight
                            - LayoutConstants.barHeight
                            + 2
                            * (LayoutConstants.maxValue - record
                            .getNumOfStudents()),
                    LayoutConstants.barWidth, 2 * record.getNumOfStudents());
            g.setColor(Color.red);
            g.drawString(record.getName(),
                    LayoutConstants.xOffset + (i + 1)
                            * LayoutConstants.barSpacing + i
                            * LayoutConstants.barWidth, LayoutConstants.yOffset
                            + LayoutConstants.graphHeight + 20);
        }
    }

    /**
     * Informs this observer that the observed CourseData object has changed
     *
     * @param o the observed CourseData object that has changed
     */
    public void update(Observable o) {
        CourseData data = (CourseData) o;
        this.courseData = data.getUpdate();

        this.setPreferredSize(new Dimension(2 * LayoutConstants.xOffset
                + (LayoutConstants.barSpacing + LayoutConstants.barWidth)
                * this.courseData.size(), LayoutConstants.graphHeight + 2
                * LayoutConstants.yOffset));
        this.revalidate();
        this.repaint();
    }

    @Override
    public void update(Object args) {
        CourseRecord record = (CourseRecord) args;
        boolean found = false;
        for (int i = 0; i < courseData.size(); i++) {
            if (courseData.get(i).getName().equals(record.getName())) {
                found = true;
                courseData.set(i, record);
            }
        }
        if (!found) {
            courseData.add(record);
        }

        this.setPreferredSize(new Dimension(2 * LayoutConstants.xOffset
                + (LayoutConstants.barSpacing + LayoutConstants.barWidth)
                * this.courseData.size(), LayoutConstants.graphHeight + 2
                * LayoutConstants.yOffset));
        this.revalidate();
        this.repaint();
    }
}