package observer.pattern;

import java.util.Vector;

import javax.swing.JOptionPane;

import observer.CourseRecord;

/**
 * Represents a vector of CourseRecords.
 */
public class CourseData extends Observable {
	private Vector<CourseRecord> courseData;
	private Vector<Observer> courseAdditionObservers;
	private Vector<Observer> courseChangeObservers;

	/**
	 * Constructs a CourseData object
	 */
	public CourseData() {
		this.courseData = new Vector<CourseRecord>();
		this.courseAdditionObservers = new Vector<Observer>();
		this.courseChangeObservers = new Vector<Observer>();
	}

	public void attach(Observer o, boolean subscribeAddition, boolean subscribeChange) {
		super.attach(o);
		if(subscribeAddition) this.courseAdditionObservers.add(o);
		if(subscribeChange) this.courseChangeObservers.add(o);
	}

	/**
	 * Add a new CourseRecord object
	 *
	 * @param courseRecord
	 *            the CourseRecord to be added
	 */
	public void addCourseRecord(CourseRecord courseRecord) {
		boolean alreadyExists = false;
		for (int i = 0; i < courseData.size(); i++) {
			CourseRecord record = courseData.elementAt(i);
			if (record.getName().equals(courseRecord.getName())) {
				alreadyExists = true;
				JOptionPane
						.showMessageDialog(
								null,
								"Warning: Attempt to add new course with an already existing name",
								"alert", JOptionPane.ERROR_MESSAGE);
				i = courseData.size(); // exit the loop
			}
		}
		if (!alreadyExists) {
			this.courseData.addElement(courseRecord);
			this.notifyObservers(courseRecord, false, true);
		}
	}

	/**
	 * Update an existing CourseRecord object
	 *
	 * @param subjectName
	 *            the name CourseRecord to be updated
	 * @param numOfStudents
	 *            the new number of students for this course
	 */
	public void changeCourseRecord(String subjectName, int numOfStudents) {
		for (int i = 0; i < courseData.size(); i++) {
			CourseRecord record = courseData.elementAt(i);
			if (record.getName().equals(subjectName)) {
				record.setNumOfStudents(numOfStudents);
				i = courseData.size();
				this.notifyObservers(record, true, false);
			}
		}
	}

	/**
	 * Return a copy of the vector of course data. Used by Observers to pull
	 * data.
	 *
	 * @return vector of course data
	 */
	public Vector<CourseRecord> getUpdate() {
		return (Vector<CourseRecord>) courseData.clone();
	}

	public void notifyObservers(Object args, boolean courseChanged, boolean courseAdded) {
		if(courseAdded){
			for(int i = 0; i < courseAdditionObservers.size(); i++){
				Observer observer = courseAdditionObservers.elementAt(i);
				observer.update(args);
			}
		}

		if(courseChanged){
			for(int i = 0; i < courseChangeObservers.size(); i++){
				Observer observer = courseChangeObservers.elementAt(i);
				observer.update(args);
			}
		}
	}
}