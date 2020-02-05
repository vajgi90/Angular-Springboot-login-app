export interface UserUpdate {
  username: string;
  password?: string;
  firstName: string;
  lastName: string;
  birthdate: Date;
  budget: number;
}