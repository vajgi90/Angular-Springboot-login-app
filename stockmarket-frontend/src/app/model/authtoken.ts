 
export class AuthToken {
  constructor(
    public access_token: string,
    public token_type: string,
    public refresh_token: string,
    public expires_in: number,
    public scope: string,
    public jti: string
  ) {}

  get token() {
    let now = new Date();
    let result = now.setSeconds(now.getSeconds() + 3600);
    let moment = new Date().getTime();
    if (!this.expires_in || moment > result) {
      return null;
    }
    return this.access_token;
  }
}