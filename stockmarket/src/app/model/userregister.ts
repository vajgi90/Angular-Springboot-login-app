export class UserRegister {
  constructor(
    public username: string,
    public password: number,
    public firstName: string,
    public lastName: string,
    public birthdate: Date
  ) {}
}