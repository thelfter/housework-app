import { User } from "./user.model";

export class Task {
  id: number;
  taskName: string;
  taskDescription: string;
  score: string;
  createdDate: Date;
  isCompleted: boolean;
  available: boolean;
  user: User;
}
