
package com.communityratesgames.platform;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

// Conflicts with org.springframework.data.repository.Repository, so be explicit.
@org.springframework.stereotype.Repository
public interface PlatformRepository extends JpaRepository<PlatformEntity, Integer> {

    PlatformEntity findById(Long id);
}
