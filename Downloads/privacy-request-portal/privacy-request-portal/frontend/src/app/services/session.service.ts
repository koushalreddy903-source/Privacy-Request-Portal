import { Injectable } from '@angular/core';

type SessionRole = 'USER' | 'ADMIN';

interface ActiveSession {
  username: string;
  role: SessionRole;
  authToken: string;
}

@Injectable({
  providedIn: 'root'
})
export class SessionService {
  private readonly storageKey = 'privacy-center-session';

  beginSession(username: string, password: string): void {
    const role: SessionRole = username === 'admin' ? 'ADMIN' : 'USER';
    const authToken = 'Basic ' + btoa(`${username}:${password}`);

    const data: ActiveSession = {
      username,
      role,
      authToken
    };

    sessionStorage.setItem(this.storageKey, JSON.stringify(data));
  }

  endSession(): void {
    sessionStorage.removeItem(this.storageKey);
  }

  isAuthenticated(): boolean {
    return !!this.readSession();
  }

  currentUsername(): string {
    return this.readSession()?.username ?? '';
  }

  currentRole(): SessionRole | '' {
    return this.readSession()?.role ?? '';
  }

  token(): string {
    return this.readSession()?.authToken ?? '';
  }

  private readSession(): ActiveSession | null {
    if (typeof window === 'undefined') {
      return null;
    }

    const raw = sessionStorage.getItem(this.storageKey);
    return raw ? JSON.parse(raw) as ActiveSession : null;
  }
}