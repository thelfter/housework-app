import { User } from "./user.model";


export class Room {
  id: number;
  name: string;
  owner: User;
}