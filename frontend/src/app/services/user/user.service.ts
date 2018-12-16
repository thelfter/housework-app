import { Injectable } from '@angular/core';
import { HttpService } from '../../http.service';
import { AuthService } from '../../auth.service';
import { Task } from '../../models/task.model';
import { User } from '../../models/user.model';

@Injectable()
export class UserService {

  private route = 'users';

  constructor(private httpService: HttpService,
              private authService: AuthService) { }

  public getUsers(): Promise<User[]> {
    return this.httpService.get<User[]>(this.route);
  }

  public getUser(userId: number): Promise<User> {
    return this.httpService.get<User>(this.route + '/' + userId);
  }

  public register(user: User, roomId: number): Promise<User> {
    return this.httpService.post<User>('register?roomId='+roomId, user);
  }

  public removeUser(userId: number): Promise<void> {
    return this.httpService.delete<void>(this.route + '/' + userId);
  }

  public increasePoints(userId: number, score: number): Promise<User> {
    return this.httpService.put<User>(this.route + '/'+ userId +'/add-score?scorePoint=' + score, []);
  }

  public assignTask(userId: number, taskId: number): Promise<User> {
    return this.httpService.put<User>(this.route + '/'+ userId +'/assign/' + taskId, []);
  }

  public unassignTask(userId: number, taskId: number): Promise<User> {
    return this.httpService.put<User>(this.route + '/'+ userId +'/unassign/' + taskId, []);
  }

  
}
