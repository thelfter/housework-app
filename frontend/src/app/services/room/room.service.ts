import { Injectable } from '@angular/core';
import { HttpService } from '../../http.service';
import { Room } from '../../models/room.model';

@Injectable()
export class RoomService {

  private route = 'rooms';

  constructor(private httpService: HttpService) { }

  public getRooms(): Promise<Room[]> {
    return this.httpService.get<Room[]>(this.route);
  }

  public getRoom(roomId: number): Promise<Room> {
    return this.httpService.get<Room>(this.route + '/' + roomId);
  }

}
