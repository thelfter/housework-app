import { Task } from './task.model';
import { Room } from './room.model';

class Roles {
  user?: boolean;
  owner?: boolean;
}

export class User {
  id: number;
  username: string;
  password: string;
  fullname: string;
  lastLogin: string;
  role: string;
  tasks: Task[];
  score: number;
  //scoreSum: number;
  //scoreActual: number;
  room: Room;
}
