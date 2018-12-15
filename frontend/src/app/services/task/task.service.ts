import { Injectable } from '@angular/core';
import { HttpService } from '../../http.service';
import { AuthService } from '../../auth.service';
import { Task } from '../../models/task.model';
import { User } from '../../models/user.model';

@Injectable()
export class TaskService {

  private route = 'tasks';

  constructor(private httpService: HttpService,
              private authService: AuthService) { }

  public getTasks(): Promise<Task[]> {
    return this.httpService.get<Task[]>(this.route);
  }

  public getTask(taskId: number): Promise<Task> {
    return this.httpService.get<Task>(this.route + '/' + taskId);
  }

  public addTask(task: Task): Promise<Task> {
    return this.httpService.post<Task>(this.route, task);
  }

  public editTask(taskId: number, task: Task): Promise<Task> {
    return this.httpService.patch<Task>(this.route + '/' + taskId, task);
  }

  public completeTask(taskId: number): Promise<Task> {
    return this.httpService.put<Task>(this.route + '/' + taskId + '/finished', []);
  }

  public removeTask(taskId: number): Promise<Task> {
    return this.httpService.delete<Task>(this.route + '/' + taskId);
  }
}
