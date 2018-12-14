class Roles {
  user?: boolean;
  owner?: boolean;
}

export class User {
  id: number;
  username: string;
  name: string;
  role: Roles;
  scoreSum: number;
  scoreActual: number;
}
