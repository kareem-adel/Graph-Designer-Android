package com.kareem_adel.graphdesigner;

import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.Hashtable;

/**
 * Created by Kareem-Adel on 1/30/2016.
 */
class GraphMap {

    static Hashtable<NodeKey, MapNode> nodeKeyMapNodeHashtable = new Hashtable<NodeKey, MapNode>();
    static Bitmap indoorMapImage;

    public static Hashtable<NodeKey, MapNode> InitGraph() {
        /*
        String MapData = "273,788-273,698-165,786-327,786-name=tag\n" +
                "383,1109-386,1161-186,1110-383,883-name=tag\n" +
                "390,577-420,579-328,579-392,606-name=tag\n" +
                "835,130-894,128-634,131-name=tag\n" +
                "912,1202-1064,1049-name=tag\n" +
                "273,698-270,671-273,788-328,697-name=tag\n" +
                "1085,1041-1127,1043-1064,1049-1083,630-name=tag\n" +
                "341,310-415,310-339,491-268,308-name=tag\n" +
                "392,606-390,577-393,698-name=tag\n" +
                "723,944-636,942-719,886-720,1042-name=tag\n" +
                "820,1075-974,922-472,1075-name=tag\n" +
                "488,309-610,308-415,310-name=tag\n" +
                "165,786-169,658-165,810-273,788-name=tag\n" +
                "543,884-544,912-383,883-name=tag\n" +
                "415,310-488,309-341,310-415,487-name=tag\n" +
                "167,518-168,486-117,518-169,658-name=tag\n" +
                "549,552-549,630-496,552-494,578-name=tag\n" +
                "328,579-328,539-390,577-326,608-name=tag\n" +
                "922,522-836,524-923,309-name=tag\n" +
                "634,280-660,281-610,280-name=tag\n" +
                "610,280-634,280-610,308-name=tag\n" +
                "1127,1235-1127,1043-1046,1154-name=tag\n" +
                "894,128-893,46-835,130-1011,244-name=tag\n" +
                "923,761-836,763-name=tag\n" +
                "660,281-659,308-634,280-634,131-name=tag\n" +
                "339,491-265,490-415,487-341,310-name=tag\n" +
                "549,630-547,760-494,644-549,552-name=tag\n" +
                "117,518-167,518-117,658-name=tag\n" +
                "836,308-836,524-923,309-748,309-name=tag\n" +
                "168,486-167,309-265,490-167,518-name=tag\n" +
                "117,658-117,518-118,811-169,658-name=tag\n" +
                "835,884-836,763-719,886-name=tag\n" +
                "923,309-922,522-836,308-name=tag\n" +
                "472,1075-820,1075-386,1161-name=tag\n" +
                "415,487-339,491-415,310-name=tag\n" +
                "548,786-550,824-547,760-499,786-name=tag\n" +
                "544,912-543,884-575,943-521,914-name=tag\n" +
                "311,1234-386,1161-186,1110-name=tag\n" +
                "547,760-548,786-549,630-name=tag\n" +
                "169,658-167,518-165,786-117,658-220,676-name=tag\n" +
                "165,810-165,786-118,811-167,884-name=tag\n" +
                "1064,1049-1085,1041-912,1202-name=tag\n" +
                "974,922-974,602-820,1075-name=tag\n" +
                "1046,1154-1127,1235-name=tag\n" +
                "635,46-634,131-893,46-29,45-name=tag\n" +
                "659,308-748,309-660,281-name=tag\n" +
                "459,580-494,578-459,537-420,579-name=tag\n" +
                "610,308-610,280-488,309-name=tag\n" +
                "268,308-265,490-341,310-167,309-name=tag\n" +
                "1127,46-1127,1043-893,46-name=tag\n" +
                "719,886-723,944-719,737-835,884-name=tag\n" +
                "383,883-383,1109-167,884-543,884-name=tag\n" +
                "719,737-719,886-835,736-name=tag\n" +
                "393,698-328,697-393,787-392,606-name=tag\n" +
                "525,839-525,866-499,825-550,824-name=tag\n" +
                "327,786-393,787-273,788-328,697-name=tag\n" +
                "393,787-393,698-327,786-420,787-name=tag\n" +
                "720,1042-723,944-name=tag\n" +
                "835,736-719,737-836,763-837,568-name=tag\n" +
                "974,602-1010,565-974,922-name=tag\n" +
                "836,524-749,523-922,522-836,308-name=tag\n" +
                "494,578-494,644-496,552-549,552-459,580-name=tag\n" +
                "220,676-169,658-270,671-name=tag\n" +
                "636,942-575,943-723,944-name=tag\n" +
                "893,46-894,128-1127,46-635,46-name=tag\n" +
                "499,825-499,786-550,824-525,839-name=tag\n" +
                "28,131-29,45-28,194-name=tag\n" +
                "265,490-268,308-339,491-168,486-name=tag\n" +
                "186,1110-311,1234-165,1088-383,1109-name=tag\n" +
                "521,914-544,912-525,866-name=tag\n" +
                "496,552-494,578-549,552-name=tag\n" +
                "1011,244-894,128-1010,565-name=tag\n" +
                "748,309-836,308-749,523-659,308-name=tag\n" +
                "420,579-459,580-390,577-name=tag\n" +
                "1127,1043-1127,1235-1085,1041-1127,46-name=tag\n" +
                "499,786-420,787-499,825-548,786-492,702-name=tag\n" +
                "29,45-635,46-28,131-name=tag\n" +
                "328,697-273,698-393,698-327,786-326,608-name=tag\n" +
                "326,608-328,579-328,697-name=tag\n" +
                "575,943-544,912-636,942-name=tag\n" +
                "420,787-393,787-499,786-name=tag\n" +
                "550,824-499,825-548,786-525,839-name=tag\n" +
                "459,537-459,580-328,539-name=tag\n" +
                "492,702-494,644-499,786-name=tag\n" +
                "118,811-117,658-165,810-29,811-name=tag\n" +
                "167,884-165,1088-30,885-165,810-383,883-name=tag\n" +
                "751,568-837,568-749,523-name=tag\n" +
                "30,885-29,959-167,884-29,811-name=tag\n" +
                "1010,565-1011,244-974,602-1083,630-name=tag\n" +
                "165,1088-186,1110-167,884-29,959-name=tag\n" +
                "29,811-30,885-118,811-28,194-name=tag\n" +
                "28,194-28,131-29,811-name=tag\n" +
                "1083,630-1010,565-1085,1041-name=tag\n" +
                "386,1161-472,1075-311,1234-383,1109-name=tag\n" +
                "837,568-835,736-751,568-name=tag\n" +
                "270,671-220,676-273,698-name=tag\n" +
                "836,763-835,736-835,884-923,761-name=tag\n" +
                "634,131-635,46-835,130-660,281-name=tag\n" +
                "749,523-751,568-836,524-748,309-name=tag\n" +
                "494,644-549,630-492,702-494,578-name=tag\n" +
                "328,539-459,537-328,579-name=tag\n" +
                "525,866-521,914-525,839-name=tag\n" +
                "167,309-268,308-168,486-name=tag\n" +
                "29,959-165,1088-30,885-name=tag";
                */
/*
        String MapData = "122,195-122,209-149,159-NA=NA\n" +
                "549,209-551,193-550,224-578,209-488,209-NA=NA\n" +
                "286,195-321,154-285,209-NA=NA\n" +
                "176,195-175,209-149,159-NA=NA\n" +
                "80,596-80,545-NA=NA\n" +
                "488,209-461,209-488,195-489,224-549,209-NA=NA\n" +
                "285,209-263,209-286,195-288,224-349,209-NA=NA\n" +
                "696,212-665,209-NA=NA\n" +
                "488,195-523,148-488,209-NA=NA\n" +
                "349,209-351,196-352,226-383,209-285,209-NA=NA\n" +
                "382,224-383,209-382,250-NA=NA\n" +
                "578,193-578,209-615,146-NA=NA\n" +
                "345,253-352,226-NA=NA\n" +
                "633,251-639,225-NA=NA\n" +
                "443,209-444,189-461,209-401,209-NA=NA\n" +
                "128,357-82,357-171,357-128,318-NA=NA\n" +
                "254,253-263,225-NA=NA\n" +
                "128,318-128,357-NA=NA\n" +
                "202,209-175,209-203,192-203,224-263,209-NA=NA\n" +
                "615,146-578,193-638,196-NA=NA\n" +
                "352,226-349,209-345,253-NA=NA\n" +
                "292,250-288,224-NA=NA\n" +
                "214,249-203,224-NA=NA\n" +
                "81,427-36,427-80,461-82,357-NA=NA\n" +
                "168,315-171,357-168,286-NA=NA\n" +
                "263,209-263,195-263,225-202,209-285,209-NA=NA\n" +
                "117,545-80,545-NA=NA\n" +
                "401,209-383,209-402,190-443,209-NA=NA\n" +
                "383,209-382,224-401,209-349,209-NA=NA\n" +
                "288,224-292,250-285,209-NA=NA\n" +
                "676,251-667,226-NA=NA\n" +
                "665,209-667,226-665,196-696,212-637,209-NA=NA\n" +
                "639,225-633,251-637,209-NA=NA\n" +
                "667,226-676,251-665,209-NA=NA\n" +
                "550,224-549,209-541,252-NA=NA\n" +
                "523,148-488,195-551,193-NA=NA\n" +
                "80,461-80,498-81,427-36,461-NA=NA\n" +
                "171,357-128,357-168,315-NA=NA\n" +
                "168,286-168,315-175,209-145,250-106,288-NA=NA\n" +
                "541,252-550,224-NA=NA\n" +
                "203,192-202,209-240,143-NA=NA\n" +
                "425,168-402,190-444,189-NA=NA\n" +
                "80,498-80,461-80,545-43,498-NA=NA\n" +
                "203,224-202,209-214,249-NA=NA\n" +
                "486,252-489,224-NA=NA\n" +
                "122,209-175,209-145,250-122,195-NA=NA\n" +
                "82,325-82,357-23,325-NA=NA\n" +
                "23,325-82,325-NA=NA\n" +
                "551,193-523,148-549,209-NA=NA\n" +
                "175,209-122,209-145,250-202,209-176,195-168,286-NA=NA\n" +
                "447,254-460,225-NA=NA\n" +
                "263,225-263,209-254,253-NA=NA\n" +
                "43,498-80,498-NA=NA\n" +
                "149,159-122,195-176,195-NA=NA\n" +
                "402,190-425,168-401,209-NA=NA\n" +
                "587,250-578,224-NA=NA\n" +
                "461,209-443,209-460,225-488,209-NA=NA\n" +
                "82,357-81,427-82,325-128,357-NA=NA\n" +
                "489,224-488,209-486,252-NA=NA\n" +
                "106,288-49,289-145,250-168,286-NA=NA\n" +
                "145,250-122,209-106,288-175,209-168,286-NA=NA\n" +
                "351,196-321,154-349,209-NA=NA\n" +
                "578,224-587,250-578,209-NA=NA\n" +
                "444,189-425,168-443,209-NA=NA\n" +
                "80,545-80,498-80,596-117,545-NA=NA\n" +
                "36,461-80,461-NA=NA\n" +
                "49,289-106,288-NA=NA\n" +
                "382,250-382,224-NA=NA\n" +
                "665,196-669,154-665,209-NA=NA\n" +
                "638,196-615,146-637,209-NA=NA\n" +
                "460,225-461,209-447,254-NA=NA\n" +
                "637,209-639,225-638,196-665,209-578,209-NA=NA\n" +
                "263,195-240,143-263,209-NA=NA\n" +
                "36,427-81,427-NA=NA\n" +
                "240,143-203,192-263,195-NA=NA\n" +
                "578,209-578,224-578,193-637,209-549,209-NA=NA\n" +
                "321,154-351,196-286,195-NA=NA\n" +
                "669,154-665,196-NA=NA";

        String[] MapNodesWithAdj = MapData.split("\n");

        for (int i = 0; i < MapNodesWithAdj.length; i++) {
            String[] NodeXY;
            String[] NameTag;

            String[] NodeAdjNameTag = MapNodesWithAdj[i].split("-");
            NodeXY = NodeAdjNameTag[0].split(",");
            NameTag = NodeAdjNameTag[NodeAdjNameTag.length - 1].split("=");

            int x = Integer.parseInt(NodeXY[0]);
            int y = Integer.parseInt(NodeXY[1]);
            InsertNewMapNode(x, y);
            getMapNode(x, y).setNodeName(NameTag[0]);
            getMapNode(x, y).setNodeTag(NameTag[1]);

        }
        for (int i = 0; i < MapNodesWithAdj.length; i++) {
            if (MapNodesWithAdj[i].split("-").length > 2) {
                String[] NodeWithAdjs = MapNodesWithAdj[i].split("-");
                String[] NodeXY = NodeWithAdjs[0].split(",");
                ArrayList<MapNode> mapNodesAdj = new ArrayList<>();
                for (int j = 1; j < NodeWithAdjs.length - 1; j++) {
                    String[] AdjNodeXY = NodeWithAdjs[j].split(",");
                    mapNodesAdj.add(getMapNode(Integer.parseInt(AdjNodeXY[0]), Integer.parseInt(AdjNodeXY[1])));
                }
                getMapNode(Integer.parseInt(NodeXY[0]), Integer.parseInt(NodeXY[1])).setAdjNodes(mapNodesAdj);
            }
        }



/*
        InsertNewMapNode(2, 1);
        InsertNewMapNode(4, 1);
        InsertNewMapNode(7, 1);
        InsertNewMapNode(2, 2);
        InsertNewMapNode(4, 2);
        InsertNewMapNode(6, 3);
        InsertNewMapNode(1, 4);
        InsertNewMapNode(2, 4);
        InsertNewMapNode(3, 4);
        InsertNewMapNode(4, 4);
        InsertNewMapNode(3, 5);
        InsertNewMapNode(1, 6);
        InsertNewMapNode(3, 6);
        InsertNewMapNode(6, 6);
        InsertNewMapNode(3, 7);
        InsertNewMapNode(6, 7);
        InsertNewMapNode(8, 7);


        // Defining adjacentNodes
        getMapNode(2, 1).setAdjNodes(new ArrayList<MapNode>() {{
            add(getMapNode(4, 1));
            add(getMapNode(2, 2));
        }});
        getMapNode(4, 1).setAdjNodes(new ArrayList<MapNode>() {{
            add(getMapNode(2, 1));
            add(getMapNode(4, 2));
            add(getMapNode(7, 1));
        }});
        getMapNode(7, 1).setAdjNodes(new ArrayList<MapNode>() {{
            add(getMapNode(4, 1));
        }});
        getMapNode(2, 2).setAdjNodes(new ArrayList<MapNode>() {{
            add(getMapNode(2, 1));
            add(getMapNode(2, 4));
        }});
        getMapNode(4, 2).setAdjNodes(new ArrayList<MapNode>() {{
            add(getMapNode(4, 1));
            add(getMapNode(6, 3));
            add(getMapNode(4, 4));
        }});
        getMapNode(6, 3).setAdjNodes(new ArrayList<MapNode>() {{
            add(getMapNode(4, 2));
            add(getMapNode(6, 6));
        }});
        getMapNode(1, 4).setAdjNodes(new ArrayList<MapNode>() {{
            add(getMapNode(2, 4));
            add(getMapNode(1, 6));
        }});
        getMapNode(2, 4).setAdjNodes(new ArrayList<MapNode>() {{
            add(getMapNode(1, 4));
            add(getMapNode(3, 4));
        }});
        getMapNode(3, 4).setAdjNodes(new ArrayList<MapNode>() {{
            add(getMapNode(2, 4));
            add(getMapNode(3, 5));
            add(getMapNode(4, 4));
        }});
        getMapNode(4, 4).setAdjNodes(new ArrayList<MapNode>() {{
            add(getMapNode(3, 4));
            add(getMapNode(4, 2));
        }});
        getMapNode(3, 5).setAdjNodes(new ArrayList<MapNode>() {{
            add(getMapNode(3, 4));
            add(getMapNode(3, 6));
        }});
        getMapNode(1, 6).setAdjNodes(new ArrayList<MapNode>() {{
            add(getMapNode(1, 4));
            add(getMapNode(3, 6));
        }});
        getMapNode(3, 6).setAdjNodes(new ArrayList<MapNode>() {{
            add(getMapNode(3, 5));
            add(getMapNode(1, 6));
            add(getMapNode(3, 7));
            add(getMapNode(6, 6));
        }});
        getMapNode(6, 6).setAdjNodes(new ArrayList<MapNode>() {{
            add(getMapNode(6, 3));
            add(getMapNode(3, 6));
            add(getMapNode(6, 7));
        }});
        getMapNode(3, 7).setAdjNodes(new ArrayList<MapNode>() {{
            add(getMapNode(3, 6));
            add(getMapNode(6, 7));
        }});
        getMapNode(6, 7).setAdjNodes(new ArrayList<MapNode>() {{
            add(getMapNode(6, 6));
            add(getMapNode(3, 7));
            add(getMapNode(8, 7));
        }});
        getMapNode(8, 7).setAdjNodes(new ArrayList<MapNode>() {{
            add(getMapNode(6, 7));
        }});
*/
        return nodeKeyMapNodeHashtable;

    }

    public static void RemoveMapNode(int x, int y) {
        getMapNode(x, y).isDeleted = true;
        nodeKeyMapNodeHashtable.remove(new NodeKey(x, y));
    }

    public static void InsertNewMapNode(int x, int y) {
        nodeKeyMapNodeHashtable.put(new NodeKey(x, y), new MapNode(x, y));
    }

    public static MapNode getMapNode(int x, int y) {
        return nodeKeyMapNodeHashtable.get(new NodeKey(x, y));
    }

    public static Bitmap getIndoorMapImage() {
        return indoorMapImage;
    }

    public static void setIndoorMapImage(Bitmap mIndoorMapImage) {
        indoorMapImage = mIndoorMapImage;
    }

    public static Hashtable<NodeKey, MapNode> getNodeKeyMapNodeHashtable() {
        return nodeKeyMapNodeHashtable;
    }

    public static void setNodeKeyMapNodeHashtable(Hashtable<NodeKey, MapNode> nodeKeyMapNodeHashtable) {
        GraphMap.nodeKeyMapNodeHashtable = nodeKeyMapNodeHashtable;
    }
}
