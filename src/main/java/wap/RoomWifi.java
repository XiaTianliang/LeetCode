package wap;

/**
 * Created by tianliangxia on 16-10-24.
 */

import java.io.File;
import java.io.FileInputStream;
import java.util.*;

/***
 * 退火算法  选择有和没有wifi的房间交换
 * 如果满意度增加 接受 否则不接受
 * 没有确定的答案
 */
class Room{
    public final int id;
    public final int stf;
    private List<Room> adjRooms = new ArrayList<>();
    private List<Room> wifiSources = new LinkedList<>(); //给当前房间贡献wifi的房间
    public Room(int id, int stf){
        this.id = id;
        this.stf = stf;
    }
    public void addAdjRoom(Room room){
        adjRooms.add(room);
    }
    public List<Room> getAdjRooms(){
        return adjRooms;
    }
    public void addWifiSource(Room room){
        wifiSources.add(room);
        //将与之相关联的房间更新
        if(room == this){
            for(Room r : adjRooms){
                r.addWifiSource(room);
            }
        }
    }
    public void removeWifeSource(Room room){
        wifiSources.remove(room);
        if(this == room){
            for(Room r : adjRooms){
                r.removeWifeSource(room);
            }
        }
    }
    public List<Room> getWifiRooms(){
        return wifiSources;
    }
    public boolean hasWifi(){
        return wifiSources.size()>0;
    }
}

class Utils{
    private static Random random = new Random();
    public static int next(int low_lim, int up_lim){
        return random.nextInt()%(up_lim - low_lim) + low_lim;
    }
    public static int next(int up_lim){
        return random.nextInt(up_lim);
    }
}

public class RoomWifi {

    public static int calStatisfaction(Room[] rooms){
        int stf = 0;
        for (Room r : rooms){
            if(r.hasWifi()){
                stf+=r.stf;
            }
        }
        return stf;
    }

    public static void main(String[] args) throws Exception{
        File inputFile = new File("./input.txt");
        FileInputStream fileInputStream= new FileInputStream(inputFile);
        Scanner scanner = new Scanner(fileInputStream);

        //Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        Room[] rooms = new Room[n];
        int m = scanner.nextInt();
        for(int i=0;i<n;i++){
            rooms[i] = new Room(i, scanner.nextInt());
        }
        int r1=0,r2=0;
        for(int i=0;i<n-1;i++){
            r1 = scanner.nextInt();
            r2 = scanner.nextInt();
            rooms[r1-1].addAdjRoom(rooms[r2-1]);
            rooms[r2-1].addAdjRoom(rooms[r1-1]);
        }

        //初始生成两个链表  不需要随机  表示有wifi和没有wifi的房间
        List<Integer> wifiRooms = new LinkedList<>();
        List<Integer> noneWifiRooms = new LinkedList<>();
        for(int i=0;i<m;i++){
            wifiRooms.add(i);
            rooms[i].addWifiSource(rooms[i]);

        }
        for(int i=m;i<n;i++){
            noneWifiRooms.add(i);
        }

        int satisfaction = calStatisfaction(rooms);
        System.out.println("Init satisfaction: " + satisfaction);

        //退火  随机取有wifi与没有wifi的房间交换
        int times = 5;
        Room roomAddWif = null, roomRemoveWifi = null;
        int maxSatisfaction = 0;
        while (times-->0){
            r1 = Utils.next(m);
            r2 = Utils.next(n-m);
            roomRemoveWifi = rooms[wifiRooms.get(r1)];
            roomAddWif = rooms[noneWifiRooms.get(r2)];
            wifiRooms.remove(r1);
            wifiRooms.add(r2);

            noneWifiRooms.remove(r2);
            noneWifiRooms.add(r1);
            System.out.println("remove: " + roomRemoveWifi.id + "  add: " + roomAddWif.id);
            roomRemoveWifi.removeWifeSource(roomRemoveWifi);
            roomAddWif.addWifiSource(roomAddWif);
            satisfaction = calStatisfaction(rooms);
            if(satisfaction > maxSatisfaction){
                maxSatisfaction = satisfaction;
            }
            System.out.println("time : " + times +  "  satisfaction: " + satisfaction);
        }
    }


}
