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
  fullName: string;
  lastLogin: string;
  role: string;
  tasks: Task[];
  sumScore: number;
  actualScore: number;
  room: Room;
}
