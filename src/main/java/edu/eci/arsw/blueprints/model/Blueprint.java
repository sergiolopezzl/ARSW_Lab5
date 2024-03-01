package edu.eci.arsw.blueprints.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Blueprint {

    private String author = null;

    private List<Point> points = null;

    private String name = null;

    public Blueprint(String author, String name, Point[] pnts) {
        this.author = author;
        this.name = name;
        points = new ArrayList<>(Arrays.asList(pnts));
    }

    public Blueprint(String author, String name) {
        this.author = author;
        this.name = name;
        points = new ArrayList<>();
    }

    public Blueprint(Blueprint blueprint) {
        this.name = blueprint.getName();
        this.author = blueprint.getAuthor();
        this.points = new ArrayList<>(blueprint.getPoints());
    }

    public Blueprint() {
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public List<Point> getPoints() {
        return points;
    }

    public void setPoints(List<Point> points) {
        this.points = points;
    }

    public void addPoint(Point p) {
        this.points.add(p);
    }

    public void removePoint(Point p) {
        this.points.remove(p);
    }

    @Override
    public String toString() {
        StringBuilder value = new StringBuilder("Blueprint{" + "author=" + author + ", name=" + name + ", points=(");
        for (int i = 0; i < points.size() - 1; i++) value.append(points.get(i)).append(",");
        if (points.size() > 0) value.append(points.get(points.size() - 1));
        return value.append(")}").toString();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Blueprint other = (Blueprint) obj;
        if (!Objects.equals(this.author, other.author)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (this.points.size() != other.points.size()) {
            return false;
        }
        for (int i = 0; i < this.points.size(); i++) {
            if (!this.points.get(i).equals(other.points.get(i))) {
                return false;
            }
        }

        return true;
    }


}
