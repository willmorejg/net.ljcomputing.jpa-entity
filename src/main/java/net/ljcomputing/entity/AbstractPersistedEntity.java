/**
           Copyright 2015, James G. Willmore

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 */

package net.ljcomputing.entity;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Abstract implementation of a persisted entity class.
 * 
 * @author James G. Willmore
 *
 */
@SuppressWarnings("PMD.AtLeastOneConstructor")
@MappedSuperclass
public abstract class AbstractPersistedEntity implements PersistedEntity {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = -5596912472711861850L;

  /** The id. */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID")
  protected Long id;

  /** The uuid. */
  @Column(name = "UUID", nullable = false)
  protected String uuid;

  /** The created time stamp. */
  @Column(name = "CREATED_TS", nullable = false)
  protected Long createdTs;

  /** The modified time stamp. */
  @Column(name = "MODIFIED_TS", nullable = false)
  protected Long modifiedTs;

  /**
   * Gets the id.
   *
   * @return the id
   */
  public Long getId() {
    return id;
  }

  /**
   * Sets the id.
   *
   * @param id the new id
   */
  public void setId(final Long id) {
    this.id = id;
  }

  /**
   * @see net.ljcomputing.entity.PersistedEntity#getUuid()
   */
  @Override
  public String getUuid() {
    return uuid;
  }

  /**
   * @see net.ljcomputing.entity.PersistedEntity#setUuid(java.lang.String)
   */
  @Override
  public void setUuid(final String uuid) {
    this.uuid = uuid;
  }

  /**
   * @see net.ljcomputing.entity.PersistedEntity#createUuid()
   */
  @Override
  public void createUuid() {
    if (uuid == null) {
      final UUID newUuid = UUID.randomUUID();
      uuid = newUuid.toString();
    }
  }

  /**
   * @see net.ljcomputing.entity.PersistedEntity#getCreatedTs()
   */
  @Override
  public Long getCreatedTs() {
    return createdTs;
  }

  /**
   * @see net.ljcomputing.entity.PersistedEntity#setCreatedTs(java.lang.Long)
   */
  @Override
  public void setCreatedTs(final Long createdTs) {
    this.createdTs = createdTs;
  }

  /**
   * @see net.ljcomputing.entity.PersistedEntity#getModifiedTs()
   */
  @Override
  public Long getModifiedTs() {
    return modifiedTs;
  }

  /**
   * @see net.ljcomputing.entity.PersistedEntity#setModifiedTs(java.lang.Long)
   */
  @Override
  public void setModifiedTs(final Long modifiedTs) {
    this.modifiedTs = modifiedTs;
  }

  /**
   * @see net.ljcomputing.entity.PersistedEntity#modifiedAt()
   */
  @Override
  public void modifiedAt() {
    if (createdTs == null) {
      createdTs = new Date().getTime();
    }

    modifiedTs = new Date().getTime();
  }

  /**
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return "AbstractPersistedEntity [uuid=" + uuid + ", id=" + id + ", createdTs=" + createdTs
        + ", modifiedTs=" + modifiedTs + "]";
  }
}
