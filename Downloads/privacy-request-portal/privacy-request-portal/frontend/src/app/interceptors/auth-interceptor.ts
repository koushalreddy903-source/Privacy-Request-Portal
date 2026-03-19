import { HttpInterceptorFn } from '@angular/common/http';

export const authInterceptor: HttpInterceptorFn = (req, next) => {
  if (typeof window === 'undefined') {
    return next(req);
  }

  const raw = sessionStorage.getItem('privacy-center-session');

  if (!raw) {
    return next(req);
  }

  const session = JSON.parse(raw);
  const authToken = session?.authToken;

  if (!authToken) {
    return next(req);
  }

  return next(
    req.clone({
      setHeaders: {
        Authorization: authToken
      }
    })
  );
};