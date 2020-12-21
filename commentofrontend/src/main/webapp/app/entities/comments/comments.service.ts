import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IComments } from 'app/shared/model/comments.model';
import { IPostDetails } from 'app/shared/model/postdetails.model';

type EntityResponseType = HttpResponse<IComments>;
type EntityArrayResponseType = HttpResponse<IComments[]>;

@Injectable({ providedIn: 'root' })
export class CommentsService {
  public resourceUrl = SERVER_API_URL + 'api/comment';
  public resourcePostUrl = SERVER_API_URL + 'api/post';

  constructor(protected http: HttpClient) {}

  create(comments: IComments): Observable<EntityResponseType> {
    return this.http.post<IComments>(this.resourceUrl, comments, { observe: 'response' });
  }

  update(comments: IComments): Observable<EntityResponseType> {
    return this.http.put<IComments>(`${this.resourceUrl}/${comments.id}`, comments, { observe: 'response' });
  }

  find(id: number): Observable<EntityArrayResponseType> {
    return this.http.get<IComments[]>(`${this.resourceUrl}?postId=${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IComments[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
