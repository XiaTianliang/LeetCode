package wap.annealing;
//
///**
// * Created by tianliangxia on 16-10-24.
// */
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.util.*;
//
///***
// * 退火算法  选择有和没有wifi的房间交换
// * 如果满意度增加 接受 否则不接受
// * 没有确定的答案
// */
//class Room{
//    public final int id;
//    public final int stf;
//    private List<Room> adjRooms = new ArrayList<>();
//    private List<Room> wifiSources = new LinkedList<>(); //给当前房间贡献wifi的房间
//    public Room(int id, int stf){
//        this.id = id;
//        this.stf = stf;
//    }
//    public void addAdjRoom(Room room){
//        //防止有多个门连接A B两个房间
//        if(!adjRooms.contains(room)){
//            adjRooms.add(room);
//        }
//    }
//    public List<Room> getAdjRooms(){
//        return adjRooms;
//    }
//    public void addWifiSource(Room room){
//        wifiSources.add(room);
//        //将与之相关联的房间更新
//        if(room == this){
//            for(Room r : adjRooms){
//                r.addWifiSource(room);
//            }
//        }
//    }
//    public void removeWifiSource(Room room){
//        wifiSources.remove(room);
//        if(this == room){
//            for(Room r : adjRooms){
//                r.removeWifiSource(room);
//            }
//        }
//    }
//    public List<Room> getWifiSources(){
//        return wifiSources;
//    }
//    public boolean hasWifi(){
//        return wifiSources.size()>0;
//    }
//}
//
//class Utils{
//    private static Random random = new Random();
//    public static int next(int low_lim, int up_lim){
//        return random.nextInt()%(up_lim - low_lim) + low_lim;
//    }
//    public static int next(int up_lim){
//        return random.nextInt(up_lim);
//    }
//    public static double nextDouble(){return random.nextDouble();}
//}
//
public class RoomWifi {
//
//    public static int calSatisfaction(Room[] rooms){
//        int stf = 0;
//        for (Room r : rooms){
//            if(r.hasWifi()){
//                stf+=r.stf;
//            }
//        }
//        return stf;
//    }
//
//    public static int calBenefit(Room roomRemoveWifi, Room roomAddWifi){
//        //去掉一个wifi后满意度的减少量
//        int stfRemoved = 0;
//        if(roomRemoveWifi.getWifiSources().size() == 1){
//            stfRemoved+=roomRemoveWifi.stf;
//        }
//        for (Room r : roomRemoveWifi.getAdjRooms()){
//            if (r.getWifiSources().size() == 1){
//                stfRemoved+=r.stf;
//            }
//        }
//        int stfAdded = 0;
//        if(roomAddWifi.getWifiSources().size() == 0 || roomAddWifi.getWifiSources().size() == 1 &&
//                roomAddWifi.getWifiSources().get(0) == roomRemoveWifi){
//            stfAdded+=roomAddWifi.stf;
//        }
//        for(Room r : roomAddWifi.getAdjRooms()){
//            if(r.getWifiSources().size() == 0 || r.getWifiSources().size() == 1 &&
//                    r.getWifiSources().get(0) == roomRemoveWifi){
//                stfAdded+=r.stf;
//            }
//        }
//        return stfAdded-stfRemoved;
//    }
//
    public static void main(String[] args) throws Exception{
        System.out.println(Math.log(0.01/40)/Math.log(0.95));
//        File inputFile = new File("./input_1000_100.txt");
//        FileInputStream fileInputStream= new FileInputStream(inputFile);
//        Scanner scanner = new Scanner(fileInputStream);
//
//        //Scanner scanner = new Scanner(System.in);
//
//        int n = scanner.nextInt();
//        Room[] rooms = new Room[n];
//        int m = scanner.nextInt();
//        for(int i=0;i<n;i++){
//            rooms[i] = new Room(i + 1, scanner.nextInt());
//        }
//        int r1=0,r2=0;
//        for(int i=0;i<n-1;i++){
//            r1 = scanner.nextInt();
//            r2 = scanner.nextInt();
//            rooms[r1-1].addAdjRoom(rooms[r2-1]);
//            rooms[r2-1].addAdjRoom(rooms[r1-1]);
//        }
//        fileInputStream.close();
//
//        //初始两个数组表示有wifi和没有wifi的房间
//        int[] wifiRooms = new int[m];
//        scanner = new Scanner(new FileInputStream(new File("./wifi.txt")));
//        for(int i=0;i<m;i++){
//            wifiRooms[i] = scanner.nextInt();
//            rooms[wifiRooms[i]].addWifiSource(rooms[wifiRooms[i]]);
////            rooms[i].addWifiSource(rooms[i]);
//        }
//        scanner.close();
//        int[] noneWifiRooms = new int[n-m];
//        scanner = new Scanner(new FileInputStream(new File("./noneWifi.txt")));
//        for(int i=m;i<n;i++){
//            noneWifiRooms[i-m] = scanner.nextInt();
////            noneWifiRooms[i-m] = i;
//        }
//        scanner.close();
//
//        int satisfaction = calSatisfaction(rooms);
//        System.out.println("Init satisfaction: " + satisfaction);
//
//        //退火  随机取有wifi与没有wifi的房间交换
//        int totalTimes = 10*n, times = 0;
//        Room roomAddWifi = null, roomRemoveWifi = null;
//        int maxSatisfaction = 0;
//        int rand1=0, rand2=0, tmp = 0;
//        while (times++<totalTimes){
//            rand1 = Utils.next(m);
//            rand2 = Utils.next(n-m);
//            roomRemoveWifi = rooms[wifiRooms[rand1]];
//            roomAddWifi = rooms[noneWifiRooms[rand2]];
//            //判断交换是否会导致满意度的增加 是 接受  否放弃
//
//            int benefit = calBenefit(roomRemoveWifi, roomAddWifi);
//            System.out.println("Benefit: " + benefit);
//            if(benefit <= 0){
//                //一定概率接受这种改变
//                if(Utils.nextDouble() > Math.exp(benefit * (times+1)))
//                    continue;
//            }
//            tmp = wifiRooms[rand1];
//            wifiRooms[rand1] = noneWifiRooms[rand2];
//            noneWifiRooms[rand2] = tmp;
//            System.out.println("remove: " + roomRemoveWifi.id + "  add: " + roomAddWifi.id);
//            roomRemoveWifi.removeWifiSource(roomRemoveWifi);
//            roomAddWifi.addWifiSource(roomAddWifi);
//            satisfaction = calSatisfaction(rooms);
//            if(satisfaction > maxSatisfaction){
//                maxSatisfaction = satisfaction;
//            }
//            System.out.println("time : " + times +  "  satisfaction: " + satisfaction);
//        }
//        System.out.println("Max satisfaction: " + maxSatisfaction);
    }
//
//
}
