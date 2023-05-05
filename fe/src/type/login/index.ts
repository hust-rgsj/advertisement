export interface Login {
  username: string;
  password: string;
}

export interface loginInfo {
  expire: number;
  id: number;
  token: string;
  type: number;
}

export interface LoginRes {
  code: number;
  data: loginInfo;
  msg: string;
}
