package com.kareem_adel.graphdesigner;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Kareem-Adel on 1/30/2016.
 */
public class Designer extends View implements View.OnTouchListener {
    int OriginX = 0;
    int OriginY = 0;

    MapNode SourceNode;
    MapNode DestinationNode;

    float scaleFactor = 1f;

    EditText NodeX;
    EditText NodeY;
    Button RemoveNode;

    ClipboardManager clipboard;
    EditText NodeName;
    EditText NodeTag;

    public Designer(Context context) {
        super(context);
        init();
    }

    public Designer(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public Designer(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

/*
        InsertNewMapNode(8, 7);


        //Defining adjacentNodes
        getMapNode(2, 1).setAdjNodes(new ArrayList<MapNode>() {{
            add(getMapNode(4, 1));
            add(getMapNode(2, 2));
        }});
*/

    private void init() {
        //GraphMap.InitGraph();
        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inScaled = false;
        GraphMap.setIndoorMapImage(BitmapFactory.decodeResource(getResources(), R.drawable.hannovermessev3, opts));
        setOnTouchListener(this);
        OriginX = -(GraphMap.getIndoorMapImage().getWidth() / 2);
        OriginY = -(GraphMap.getIndoorMapImage().getHeight() / 2);

        NodeX = new EditText(getContext());
        NodeX.setSingleLine(true);
        NodeX.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_SIGNED);

        NodeY = new EditText(getContext());
        NodeY.setSingleLine(true);
        NodeY.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_SIGNED);

        NodeName = new EditText(getContext());
        NodeName.setSingleLine(true);

        NodeTag = new EditText(getContext());
        NodeTag.setSingleLine(true);

        RemoveNode = new Button(getContext());

        NodeX.setOnKeyListener(new OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (keyEvent.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                    if (SourceNode != null) {
                        int x = SourceNode.getX();
                        int newx = Integer.parseInt(NodeX.getText().toString());
                        if (x != newx) {
                            SourceNode.setX(newx);
                            invalidate();
                            saveGraph();
                        }
                    }
                }
                return false;
            }
        });

        NodeY.setOnKeyListener(new OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (keyEvent.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                    if (SourceNode != null) {
                        int y = SourceNode.getY();
                        int newy = Integer.parseInt(NodeY.getText().toString());
                        if (y != newy) {
                            SourceNode.setY(newy);
                            invalidate();
                            saveGraph();
                        }
                    }
                }
                return false;
            }
        });

        NodeName.setOnKeyListener(new OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (keyEvent.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                    if (SourceNode != null) {
                        GraphMap.getMapNode(SourceNode.getX(), SourceNode.getY()).setNodeName(NodeName.getText().toString());
                        invalidate();
                        saveGraph();
                    }
                }
                return false;
            }
        });

        NodeTag.setOnKeyListener(new OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (keyEvent.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                    if (SourceNode != null) {
                        GraphMap.getMapNode(SourceNode.getX(), SourceNode.getY()).setNodeTag(NodeTag.getText().toString());
                        invalidate();
                        saveGraph();
                    }
                }
                return false;
            }
        });

        RemoveNode.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (SourceNode != null) {
                    removeNode(SourceNode);
                    GraphMap.RemoveMapNode(SourceNode.getX(), SourceNode.getY());
                    SourceNode = null;
                    clearUIXY();
                    invalidate();
                    saveGraph();
                }
            }
        });
        RemoveNode.setText("Remove Node");

        clipboard = (ClipboardManager) getContext().getSystemService(Context.CLIPBOARD_SERVICE);

        invalidate();
        saveGraph();
    }

    public void removeNode(MapNode Node) {
        while (Node.getAdjNodes().size() > 0) {
            MapNode LinkAdj = Node.getAdjNodes().remove(0);
            removeLinks(Node, LinkAdj);
        }
    }

    public void removeLinks(MapNode parent, MapNode Link) {
        Link.getAdjNodes().remove(parent);
        if ((Link.getNodeName().equals("link"))) {
            while (Link.getAdjNodes().size() > 0) {
                MapNode LinkAdj = Link.getAdjNodes().remove(0);
                removeLinks(Link, LinkAdj);
            }
            if (Link.getAdjNodes().size() == 0) {
                if (GraphMap.getMapNode(Link.getX(), Link.getY()) != null) {
                    GraphMap.RemoveMapNode(Link.getX(), Link.getY());
                }
            }
        }
    }

    public void saveGraph() {
        StringBuilder mGraph = new StringBuilder();
        Enumeration<MapNode> mapNodeEnumeration = GraphMap.getNodeKeyMapNodeHashtable().elements();
        while (mapNodeEnumeration.hasMoreElements()) {
            MapNode mapNode = mapNodeEnumeration.nextElement();
            if (mapNode == null) {
                continue;
            }
            mGraph.append(mapNode.getX()).append(",").append(mapNode.getY());
            Iterator<MapNode> mapNodeIterator = mapNode.getAdjNodes().iterator();
            while (mapNodeIterator.hasNext()) {
                MapNode adjMapNode = mapNodeIterator.next();
                if (adjMapNode.isDeleted) {
                    mapNode.getAdjNodes().remove(adjMapNode);
                    continue;
                }
                mGraph.append("-").append(adjMapNode.getX()).append(",").append(adjMapNode.getY());
            }
            mGraph.append("-").append(mapNode.getNodeName()).append("=").append(mapNode.getNodeTag());
            mGraph.append("\n");
        }

        mGraph.append("[");
        mapNodeEnumeration = GraphMap.getNodeKeyMapNodeHashtable().elements();
        while (mapNodeEnumeration.hasMoreElements()) {
            MapNode mapNode = mapNodeEnumeration.nextElement();
            if (mapNode == null) {
                continue;
            }
            mGraph.append("[").append(mapNode.getX()).append(",").append(mapNode.getY()).append("]");
            mGraph.append(",");
        }
        mGraph.append("]");
        setDataToClipboard(mGraph.toString());
    }

    public void setDataToClipboard(String Data) {
        clipboard.setPrimaryClip(ClipData.newPlainText("MapData", Data));
    }


    int centerX = 0;
    int centerY = 0;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        centerX = canvas.getWidth() / 2;
        centerY = canvas.getHeight() / 2;

        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.WHITE);
        canvas.drawPaint(paint);

        //draw Map
        canvas.save();

        canvas.translate(centerX, centerY);
        canvas.scale(scaleFactor, scaleFactor);
        canvas.translate(OriginX, OriginY);

        canvas.drawBitmap(GraphMap.getIndoorMapImage(), 0, 0, paint);


        // draw nodes
        paint.setColor(Color.parseColor("#CD5C5C"));
        Enumeration<MapNode> mapNodeEnumeration = GraphMap.getNodeKeyMapNodeHashtable().elements();
        while (mapNodeEnumeration.hasMoreElements()) {
            MapNode mapNode = mapNodeEnumeration.nextElement();
            if (mapNode == SourceNode || mapNode == DestinationNode) {
                paint.setColor(Color.parseColor("#00FF00"));
                canvas.drawCircle(mapNode.getX(), mapNode.getY(), 2.5f, paint);
                paint.setColor(Color.parseColor("#CD5C5C"));
                continue;
            }
            canvas.drawCircle(mapNode.getX(), mapNode.getY(), 2.5f, paint);
            canvas.drawText(mapNode.getNodeName(), mapNode.getX(), mapNode.getY(), paint);
        }

        //draw connections
        paint.setColor(Color.parseColor("#000000"));
        paint.setStrokeWidth(1.25f);
        mapNodeEnumeration = GraphMap.getNodeKeyMapNodeHashtable().elements();
        while (mapNodeEnumeration.hasMoreElements()) {
            MapNode mapNode = mapNodeEnumeration.nextElement();
            Iterator<MapNode> mapNodeIterator = mapNode.getAdjNodes().iterator();
            while (mapNodeIterator.hasNext()) {
                MapNode mapNodeAdj = mapNodeIterator.next();
                canvas.drawLine(mapNode.getX(), mapNode.getY(), mapNodeAdj.getX(), mapNodeAdj.getY(), paint);
            }
        }
        canvas.restore();

    }

    int oldX;
    int oldY;
    int newX;
    int newY;
    float currentPinchDistance;
    float lastPinchDistance;
    boolean wasMoving = false;
    boolean wasPinching = false;

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (this == view) {
            switch (motionEvent.getActionMasked()) {

                case MotionEvent.ACTION_DOWN: {
                    oldX = (int) ((int) motionEvent.getX() / scaleFactor);
                    oldY = (int) ((int) motionEvent.getY() / scaleFactor);
                    newX = (int) ((int) motionEvent.getX() / scaleFactor);
                    newY = (int) ((int) motionEvent.getY() / scaleFactor);
                    break;
                }
                case MotionEvent.ACTION_UP: {
                    if (wasPinching || wasMoving) {
                        wasPinching = false;
                        wasMoving = false;
                        return true;
                    }
                    int MapNodeX = (int) ((((int) motionEvent.getX() / scaleFactor) - OriginX) - (centerX / scaleFactor));
                    int MapNodeY = (int) ((((int) motionEvent.getY() / scaleFactor) - OriginY) - (centerY / scaleFactor));

                    MapNode mapNode = GraphMap.getMapNode(MapNodeX, MapNodeY);

                    if (mapNode == null) {
                        Enumeration<MapNode> mapNodeEnumeration = GraphMap.getNodeKeyMapNodeHashtable().elements();
                        if (mapNodeEnumeration.hasMoreElements()) {
                            MapNode closestMapNode = mapNodeEnumeration.nextElement();
                            while (mapNodeEnumeration.hasMoreElements()) {
                                MapNode tmpMapNode = mapNodeEnumeration.nextElement();
                                double closestDistance = Math.sqrt(Math.pow(closestMapNode.getX() - MapNodeX, 2) + Math.pow(closestMapNode.getY() - MapNodeY, 2));
                                double tmpDistance = Math.sqrt(Math.pow(tmpMapNode.getX() - MapNodeX, 2) + Math.pow(tmpMapNode.getY() - MapNodeY, 2));
                                if (closestDistance > tmpDistance) {
                                    closestMapNode = tmpMapNode;
                                }
                            }

                            double closestDistance = Math.sqrt(Math.pow(closestMapNode.getX() - MapNodeX, 2) + Math.pow(closestMapNode.getY() - MapNodeY, 2));
                            if (closestDistance < 20 / scaleFactor) {
                                mapNode = closestMapNode;
                            }
                        }

                    }

                    if (mapNode != null) {
                        if (SourceNode == mapNode) {
                            SourceNode = null;
                            clearUIXY();
                        } else if (DestinationNode == mapNode) {
                            DestinationNode = null;
                        } else if (SourceNode == null && DestinationNode == null) {
                            SourceNode = mapNode;
                            updateUIXY();
                        } else if (SourceNode != null && DestinationNode == null) {
                            DestinationNode = mapNode;
                            createConnection();
                            saveGraph();
                        } else if (SourceNode == null) {
                            SourceNode = mapNode;
                            createConnection();
                            saveGraph();
                        }
                    } else {
                        GraphMap.InsertNewMapNode(MapNodeX, MapNodeY);
                        saveGraph();
                    }
                    invalidate();
                    break;
                }
                case MotionEvent.ACTION_MOVE: {
                    if (motionEvent.getPointerCount() == 1 && wasPinching) {
                        newX = (int) ((int) motionEvent.getX() / scaleFactor);
                        newY = (int) ((int) motionEvent.getY() / scaleFactor);
                        wasPinching = false;
                    }
                    if (motionEvent.getPointerCount() == 2) {
                        float initialX = motionEvent.getX(0);
                        float initialY = motionEvent.getY(0);

                        float secondX = motionEvent.getX(1);
                        float secondY = motionEvent.getY(1);

                        currentPinchDistance = (float) Math.sqrt(Math.pow(secondX - initialX, 2) + Math.pow(secondY - initialY, 2));
                        scaleFactor += ((currentPinchDistance - lastPinchDistance) / 1000) * scaleFactor;
                        lastPinchDistance = currentPinchDistance;
                        wasPinching = true;
                    } else {
                        oldX = newX;
                        oldY = newY;
                        newX = (int) ((int) motionEvent.getX() / scaleFactor);
                        newY = (int) ((int) motionEvent.getY() / scaleFactor);

                        OriginX += newX - oldX;
                        OriginY += newY - oldY;
                    }
                    wasMoving = true;
                    invalidate();
                    break;
                }
                case MotionEvent.ACTION_POINTER_DOWN: {
                    float initialX = motionEvent.getX(0);
                    float initialY = motionEvent.getY(0);

                    float secondX = motionEvent.getX(1);
                    float secondY = motionEvent.getY(1);

                    lastPinchDistance = (float) Math.sqrt(Math.pow(secondX - initialX, 2) + Math.pow(secondY - initialY, 2));
                    break;
                }

            }


        }
        return true;
    }

    public void updateUIXY() {
        if (SourceNode != null) {
            NodeX.setText("" + SourceNode.getX());
            NodeY.setText("" + SourceNode.getY());
            NodeName.setText("" + SourceNode.getNodeName());
            NodeTag.setText("" + SourceNode.getNodeTag());
        }
    }

    public void clearUIXY() {
        NodeX.setText("");
        NodeY.setText("");
        NodeName.setText("");
        NodeTag.setText("");
    }

    public void createConnection() {
        double samples = (int) (Math.sqrt(Math.pow(DestinationNode.getX() - SourceNode.getX(), 2) + Math.pow(DestinationNode.getY() - SourceNode.getY(), 2)) / 50);
        double unitX = DestinationNode.getX() - SourceNode.getX();
        double unitY = DestinationNode.getY() - SourceNode.getY();

        ArrayList<MapNode> mapNodes = new ArrayList<>();
        for (double i = 1; i < samples; i++) {
            double linkX = (int) ((SourceNode.getX() + (unitX * (i / samples))));
            double linkY = (int) ((SourceNode.getY() + (unitY * (i / samples))));
            GraphMap.InsertNewMapNode((int) linkX, (int) linkY);
            MapNode mapNode = GraphMap.getMapNode((int) linkX, (int) linkY);
            mapNode.setNodeName("link");
            if (mapNodes.size() > 0) {
                MapNode lastLink = mapNodes.get(mapNodes.size() - 1);
                lastLink.getAdjNodes().add(mapNode);
                mapNode.getAdjNodes().add(lastLink);
            }
            mapNodes.add(mapNode);
        }

        if (mapNodes.size() > 0) {
            MapNode firstLink = mapNodes.get(0);
            MapNode lastLink = mapNodes.get(mapNodes.size() - 1);

            SourceNode.getAdjNodes().add(firstLink);
            firstLink.getAdjNodes().add(SourceNode);

            DestinationNode.getAdjNodes().add(lastLink);
            lastLink.getAdjNodes().add(DestinationNode);
        } else {
            SourceNode.getAdjNodes().add(DestinationNode);
            DestinationNode.getAdjNodes().add(SourceNode);
        }
        SourceNode = null;
        DestinationNode = null;
    }
}
