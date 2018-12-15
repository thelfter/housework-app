import { User } from "./user.model";

export class Task {
  id: number;
  taskName: string;
  taskDescription: string;
  score: string;
  createdDate: Date;
  isCompleted: boolean;
  isAvailable: boolean = true;
  user: User;
}
