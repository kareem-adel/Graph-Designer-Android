package com.kareem_adel.graphdesigner;

import java.util.ArrayList;

/**
 * Created by Kareem-Adel on 1/14/2016.
 */
public class MapNode {
    public static final int INF = 2147483647;

    private String optionalDescription;

    private int x;
    private int y;
    private int cost = INF;

    boolean isDeleted = false;

    private ArrayList<MapNode> adjNodes;

    String NodeName = "NA";
    String NodeTag = "NA";


    public MapNode(int x, int y) {
        setX(x);
        setY(y);
        setAdjNodes(new ArrayList<MapNode>());
    }

    public MapNode(String optionalName, int x, int y) {
        setOptionalDescription(optionalName);
        setX(x);
        setY(y);
        setAdjNodes(new ArrayList<MapNode>());
    }

    public String getOptionalDescription() {
        return optionalDescription;
    }

    public void setOptionalDescription(String optionalDescription) {
        this.optionalDescription = optionalDescription;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public ArrayList<MapNode> getAdjNodes() {
        return adjNodes;
    }

    public void setAdjNodes(ArrayList<MapNode> adjNodes) {
        this.adjNodes = adjNodes;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getNodeName() {
        return NodeName;
    }

    public void setNodeName(String nodeName) {
        NodeName = nodeName;
    }

    public String getNodeTag() {
        return NodeTag;
    }

    public void setNodeTag(String nodeTag) {
        NodeTag = nodeTag;
    }
/*
    @Override
    public boolean equals(Object o) {
        MapNode mapNode = (MapNode) o;
        return super.equals(this.getX() == mapNode.getX() && this.getY() == mapNode.getY());
    }
*/
}
