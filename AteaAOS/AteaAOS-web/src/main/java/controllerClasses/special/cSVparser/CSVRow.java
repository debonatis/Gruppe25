/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllerClasses.special.cSVparser;

import org.jsefa.csv.annotation.CsvDataType;
import org.jsefa.csv.annotation.CsvField;

/**
 *
 * @author simond
 */
@CsvDataType
public class CSVRow {

    @CsvField(pos = 1)
    String DN;
    @CsvField(pos = 2)
    String objectClass;
    @CsvField(pos = 3)
    String distinguishedName;
    @CsvField(pos = 4)
    String instanceType;
    @CsvField(pos = 5)
    String whenCreated;
    @CsvField(pos = 6)
    String whenChanged;
    @CsvField(pos = 7)
    String subRefs;
    @CsvField(pos = 8)
    String uSNCreated;
    @CsvField(pos = 9)
    String uSNChanged;
    @CsvField(pos = 10)
    String name;
    @CsvField(pos = 11)
    String objectGUID;
    @CsvField(pos = 12)
    String creationTime;
    @CsvField(pos = 13)
    String forceLogoff;
    @CsvField(pos = 14)
    String lockoutDuration;
    @CsvField(pos = 15)
    String lockOutObservationWindow;
    @CsvField(pos = 16)
    String lockoutThreshold;
    @CsvField(pos = 17)
    String maxPwdAge;
    @CsvField(pos = 18)
    String minPwdAge;
    @CsvField(pos = 19)
    String minPwdLength;
    @CsvField(pos = 20)
    String modifiedCountAtLastProm;
    @CsvField(pos = 21)
    String nextRid;
    @CsvField(pos = 22)
    String pwdProperties;
    @CsvField(pos = 23)
    String pwdHistoryLength;
    @CsvField(pos = 24)
    String objectSid;
    @CsvField(pos = 25)
    String serverState;
    @CsvField(pos = 26)
    String uASCompat;
    @CsvField(pos = 27)
    String modifiedCount;
    @CsvField(pos = 28)
    String auditingPolicy;
    @CsvField(pos = 29)
    String nTMixedDomain;
    @CsvField(pos = 30)
    String rIDManagerReference;
    @CsvField(pos = 31)
    String fSMORoleOwner;
    @CsvField(pos = 32)
    String systemFlags;
    @CsvField(pos = 33)
    String wellKnownObjects;
    @CsvField(pos = 34)
    String objectCategory;
    @CsvField(pos = 35)
    String isCriticalSystemObject;
    @CsvField(pos = 36)
    String gPLink;
    @CsvField(pos = 37)
    String dSCorePropagationData;
    @CsvField(pos = 38)
    String otherWellKnownObjects;
    @CsvField(pos = 39)
    String masteredBy;
    @CsvField(pos = 40)
    String msDSMachineAccountQuota;
    @CsvField(pos = 41)
    String msDSBehaviorVersion;
    @CsvField(pos = 42)
    String msDSPerUserTrustQuota;
    @CsvField(pos = 43)
    String msDSAllUsersTrustQuota;
    @CsvField(pos = 45)
    String msDSPerUserTrustTombstonesQuota;
    @CsvField(pos = 46)
    String msDsmasteredBy;
    @CsvField(pos = 47)
    String msDSIsDomainFor;
    @CsvField(pos = 48)
    String msDSNcType;
    @CsvField(pos = 50)
    String dc;
    @CsvField(pos = 51)
    String cn;
    @CsvField(pos = 52)
    String description;
    @CsvField(pos = 53)
    String showInAdvancedViewOnly;
    @CsvField(pos = 54)
    String ou;
    @CsvField(pos = 56)
    String msDSTombstoneQuotaFactor;
    @CsvField(pos = 57)
    String displayName;
    @CsvField(pos = 58)
    String flags;
    @CsvField(pos = 59)
    String versionNumber;
    @CsvField(pos = 60)
    String gPCFunctionalityVersion;
    @CsvField(pos = 61)
    String gPCFileSysPath;
    @CsvField(pos = 62)
    String gPCMachineExtensionNames;
    @CsvField(pos = 63)
    String ipsecName;
    @CsvField(pos = 64)
    String ipsecID;
    @CsvField(pos = 65)
    String ipsecDataType;
    @CsvField(pos = 66)
    String ipsecData;
    @CsvField(pos = 67)
    String ipsecISAKMPReference;
    @CsvField(pos = 68)
    String ipsecNFAReference;
    @CsvField(pos = 69)
    String ipsecOwnersReference;
    @CsvField(pos = 70)
    String ipsecNegotiationPolicyReference;
    @CsvField(pos = 71)
    String ipsecFilterReference;
    @CsvField(pos = 72)
    String iPSECNegotiationPolicyType;
    @CsvField(pos = 73)
    String iPSECNegotiationPolicyAction;
    @CsvField(pos = 74)
    String revision;
    @CsvField(pos = 75)
    String memberOf;
    @CsvField(pos = 76)
    String userAccountControl;
    @CsvField(pos = 77)
    String badPwdCount;
    @CsvField(pos = 78)
    String codePage;
    @CsvField(pos = 79)
    String countryCode;
    @CsvField(pos = 80)
    String badPasswordTime;
    @CsvField(pos = 81)
    String lastLogoff;
    @CsvField(pos = 82)
    String lastLogon;
    @CsvField(pos = 83)
    String logonHours;
    @CsvField(pos = 84)
    String pwdLastSet;
    @CsvField(pos = 85)
    String primaryGroupID;
    @CsvField(pos = 86)
    String adminCount;
    @CsvField(pos = 87)
    String accountExpires;
    @CsvField(pos = 88)
    String logonCount;
    @CsvField(pos = 89)
    String sAMAccountName;
    @CsvField(pos = 90)
    String sAMAccountType;
    @CsvField(pos = 91)
    String lastLogonTimestamp;
    @CsvField(pos = 92)
    String member;
    @CsvField(pos = 93)
    String groupType;
    @CsvField(pos = 94)
    String samDomainUpdates;
    @CsvField(pos = 95)
    String localPolicyFlags;
    @CsvField(pos = 96)
    String operatingSystem;
    @CsvField(pos = 97)
    String operatingSystemVersion;
    @CsvField(pos = 98)
    String operatingSystemServicePack;
    @CsvField(pos = 99)
    String serverReferenceBL;
    @CsvField(pos = 100)
    String dNSHostName;
    @CsvField(pos = 101)
    String rIDSetReferences;
    @CsvField(pos = 102)
    String servicePrincipalName;
    @CsvField(pos = 103)
    String msDSSupportedEncryptionTypes;
    @CsvField(pos = 104)
    String msDFSRComputerReferenceBL;
    @CsvField(pos = 105)
    String rIDAvailablePool;
    @CsvField(pos = 106)
    String rIDAllocationPool;
    @CsvField(pos = 107)
    String rIDPreviousAllocationPool;
    @CsvField(pos = 108)
    String rIDUsedPool;
    @CsvField(pos = 109)
    String rIDNextRID;
    @CsvField(pos = 110)
    String msDFSRFlags;
    @CsvField(pos = 111)
    String msDFSRReplicationGroupType;
    @CsvField(pos = 112)
    String msDFSRFileFilter;
    @CsvField(pos = 113)
    String msDFSRDirectoryFilter;
    @CsvField(pos = 114)
    String serverReference;
    @CsvField(pos = 115)
    String msDFSRComputerReference;
    @CsvField(pos = 116)
    String msDFSRMemberReferenceBL;
    @CsvField(pos = 116)
    String msDFSRVersion;
    @CsvField(pos = 117)
    String msDFSRReplicationGroupGuid;
    @CsvField(pos = 118)
    String msDFSRMemberReference;
    @CsvField(pos = 119)
    String msDFSRRootPath;
    @CsvField(pos = 120)
    String msDFSRStagingPath;
    @CsvField(pos = 121)
    String msDFSREnabled;
    @CsvField(pos = 122)
    String msDFSROptions;
    @CsvField(pos = 123)
    String msDFSRContentSetGuid;
    @CsvField(pos = 124)
    String msDFSRReadOnly;
    @CsvField(pos = 125)
    String lastSetTime;
    @CsvField(pos = 126)
    String priorSetTime;
    @CsvField(pos = 127)
    String sn;
    @CsvField(pos = 128)
    String givenName;
    @CsvField(pos = 129)
    String initials;
    @CsvField(pos = 130)
    String userPrincipalName;

    public String getDN() {
        return DN;
    }

    public void setDN(String DN) {
        this.DN = DN;
    }

    public String getObjectClass() {
        return objectClass;
    }

    public void setObjectClass(String objectClass) {
        this.objectClass = objectClass;
    }

    public String getDistinguishedName() {
        return distinguishedName;
    }

    public void setDistinguishedName(String distinguishedName) {
        this.distinguishedName = distinguishedName;
    }

    public String getInstanceType() {
        return instanceType;
    }

    public void setInstanceType(String instanceType) {
        this.instanceType = instanceType;
    }

    public String getWhenCreated() {
        return whenCreated;
    }

    public void setWhenCreated(String whenCreated) {
        this.whenCreated = whenCreated;
    }

    public String getWhenChanged() {
        return whenChanged;
    }

    public void setWhenChanged(String whenChanged) {
        this.whenChanged = whenChanged;
    }

    public String getSubRefs() {
        return subRefs;
    }

    public void setSubRefs(String subRefs) {
        this.subRefs = subRefs;
    }

    public String getuSNCreated() {
        return uSNCreated;
    }

    public void setuSNCreated(String uSNCreated) {
        this.uSNCreated = uSNCreated;
    }

    public String getuSNChanged() {
        return uSNChanged;
    }

    public void setuSNChanged(String uSNChanged) {
        this.uSNChanged = uSNChanged;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getObjectGUID() {
        return objectGUID;
    }

    public void setObjectGUID(String objectGUID) {
        this.objectGUID = objectGUID;
    }

    public String getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(String creationTime) {
        this.creationTime = creationTime;
    }

    public String getForceLogoff() {
        return forceLogoff;
    }

    public void setForceLogoff(String forceLogoff) {
        this.forceLogoff = forceLogoff;
    }

    public String getLockoutDuration() {
        return lockoutDuration;
    }

    public void setLockoutDuration(String lockoutDuration) {
        this.lockoutDuration = lockoutDuration;
    }

    public String getLockOutObservationWindow() {
        return lockOutObservationWindow;
    }

    public void setLockOutObservationWindow(String lockOutObservationWindow) {
        this.lockOutObservationWindow = lockOutObservationWindow;
    }

    public String getLockoutThreshold() {
        return lockoutThreshold;
    }

    public void setLockoutThreshold(String lockoutThreshold) {
        this.lockoutThreshold = lockoutThreshold;
    }

    public String getMaxPwdAge() {
        return maxPwdAge;
    }

    public void setMaxPwdAge(String maxPwdAge) {
        this.maxPwdAge = maxPwdAge;
    }

    public String getMinPwdAge() {
        return minPwdAge;
    }

    public void setMinPwdAge(String minPwdAge) {
        this.minPwdAge = minPwdAge;
    }

    public String getMinPwdLength() {
        return minPwdLength;
    }

    public void setMinPwdLength(String minPwdLength) {
        this.minPwdLength = minPwdLength;
    }

    public String getModifiedCountAtLastProm() {
        return modifiedCountAtLastProm;
    }

    public void setModifiedCountAtLastProm(String modifiedCountAtLastProm) {
        this.modifiedCountAtLastProm = modifiedCountAtLastProm;
    }

    public String getNextRid() {
        return nextRid;
    }

    public void setNextRid(String nextRid) {
        this.nextRid = nextRid;
    }

    public String getPwdProperties() {
        return pwdProperties;
    }

    public void setPwdProperties(String pwdProperties) {
        this.pwdProperties = pwdProperties;
    }

    public String getPwdHistoryLength() {
        return pwdHistoryLength;
    }

    public void setPwdHistoryLength(String pwdHistoryLength) {
        this.pwdHistoryLength = pwdHistoryLength;
    }

    public String getObjectSid() {
        return objectSid;
    }

    public void setObjectSid(String objectSid) {
        this.objectSid = objectSid;
    }

    public String getServerState() {
        return serverState;
    }

    public void setServerState(String serverState) {
        this.serverState = serverState;
    }

    public String getuASCompat() {
        return uASCompat;
    }

    public void setuASCompat(String uASCompat) {
        this.uASCompat = uASCompat;
    }

    public String getModifiedCount() {
        return modifiedCount;
    }

    public void setModifiedCount(String modifiedCount) {
        this.modifiedCount = modifiedCount;
    }

    public String getAuditingPolicy() {
        return auditingPolicy;
    }

    public void setAuditingPolicy(String auditingPolicy) {
        this.auditingPolicy = auditingPolicy;
    }

    public String getnTMixedDomain() {
        return nTMixedDomain;
    }

    public void setnTMixedDomain(String nTMixedDomain) {
        this.nTMixedDomain = nTMixedDomain;
    }

    public String getrIDManagerReference() {
        return rIDManagerReference;
    }

    public void setrIDManagerReference(String rIDManagerReference) {
        this.rIDManagerReference = rIDManagerReference;
    }

    public String getfSMORoleOwner() {
        return fSMORoleOwner;
    }

    public void setfSMORoleOwner(String fSMORoleOwner) {
        this.fSMORoleOwner = fSMORoleOwner;
    }

    public String getSystemFlags() {
        return systemFlags;
    }

    public void setSystemFlags(String systemFlags) {
        this.systemFlags = systemFlags;
    }

    public String getWellKnownObjects() {
        return wellKnownObjects;
    }

    public void setWellKnownObjects(String wellKnownObjects) {
        this.wellKnownObjects = wellKnownObjects;
    }

    public String getObjectCategory() {
        return objectCategory;
    }

    public void setObjectCategory(String objectCategory) {
        this.objectCategory = objectCategory;
    }

    public String getIsCriticalSystemObject() {
        return isCriticalSystemObject;
    }

    public void setIsCriticalSystemObject(String isCriticalSystemObject) {
        this.isCriticalSystemObject = isCriticalSystemObject;
    }

    public String getgPLink() {
        return gPLink;
    }

    public void setgPLink(String gPLink) {
        this.gPLink = gPLink;
    }

    public String getdSCorePropagationData() {
        return dSCorePropagationData;
    }

    public void setdSCorePropagationData(String dSCorePropagationData) {
        this.dSCorePropagationData = dSCorePropagationData;
    }

    public String getOtherWellKnownObjects() {
        return otherWellKnownObjects;
    }

    public void setOtherWellKnownObjects(String otherWellKnownObjects) {
        this.otherWellKnownObjects = otherWellKnownObjects;
    }

    public String getMasteredBy() {
        return masteredBy;
    }

    public void setMasteredBy(String masteredBy) {
        this.masteredBy = masteredBy;
    }

    public String getMsDSMachineAccountQuota() {
        return msDSMachineAccountQuota;
    }

    public void setMsDSMachineAccountQuota(String msDSMachineAccountQuota) {
        this.msDSMachineAccountQuota = msDSMachineAccountQuota;
    }

    public String getMsDSBehaviorVersion() {
        return msDSBehaviorVersion;
    }

    public void setMsDSBehaviorVersion(String msDSBehaviorVersion) {
        this.msDSBehaviorVersion = msDSBehaviorVersion;
    }

    public String getMsDSPerUserTrustQuota() {
        return msDSPerUserTrustQuota;
    }

    public void setMsDSPerUserTrustQuota(String msDSPerUserTrustQuota) {
        this.msDSPerUserTrustQuota = msDSPerUserTrustQuota;
    }

    public String getMsDSAllUsersTrustQuota() {
        return msDSAllUsersTrustQuota;
    }

    public void setMsDSAllUsersTrustQuota(String msDSAllUsersTrustQuota) {
        this.msDSAllUsersTrustQuota = msDSAllUsersTrustQuota;
    }

    public String getMsDSPerUserTrustTombstonesQuota() {
        return msDSPerUserTrustTombstonesQuota;
    }

    public void setMsDSPerUserTrustTombstonesQuota(String msDSPerUserTrustTombstonesQuota) {
        this.msDSPerUserTrustTombstonesQuota = msDSPerUserTrustTombstonesQuota;
    }

    public String getMsDsmasteredBy() {
        return msDsmasteredBy;
    }

    public void setMsDsmasteredBy(String msDsmasteredBy) {
        this.msDsmasteredBy = msDsmasteredBy;
    }

    public String getMsDSIsDomainFor() {
        return msDSIsDomainFor;
    }

    public void setMsDSIsDomainFor(String msDSIsDomainFor) {
        this.msDSIsDomainFor = msDSIsDomainFor;
    }

    public String getMsDSNcType() {
        return msDSNcType;
    }

    public void setMsDSNcType(String msDSNcType) {
        this.msDSNcType = msDSNcType;
    }

    public String getDc() {
        return dc;
    }

    public void setDc(String dc) {
        this.dc = dc;
    }

    public String getCn() {
        return cn;
    }

    public void setCn(String cn) {
        this.cn = cn;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getShowInAdvancedViewOnly() {
        return showInAdvancedViewOnly;
    }

    public void setShowInAdvancedViewOnly(String showInAdvancedViewOnly) {
        this.showInAdvancedViewOnly = showInAdvancedViewOnly;
    }

    public String getOu() {
        return ou;
    }

    public void setOu(String ou) {
        this.ou = ou;
    }

    public String getMsDSTombstoneQuotaFactor() {
        return msDSTombstoneQuotaFactor;
    }

    public void setMsDSTombstoneQuotaFactor(String msDSTombstoneQuotaFactor) {
        this.msDSTombstoneQuotaFactor = msDSTombstoneQuotaFactor;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getFlags() {
        return flags;
    }

    public void setFlags(String flags) {
        this.flags = flags;
    }

    public String getVersionNumber() {
        return versionNumber;
    }

    public void setVersionNumber(String versionNumber) {
        this.versionNumber = versionNumber;
    }

    public String getgPCFunctionalityVersion() {
        return gPCFunctionalityVersion;
    }

    public void setgPCFunctionalityVersion(String gPCFunctionalityVersion) {
        this.gPCFunctionalityVersion = gPCFunctionalityVersion;
    }

    public String getgPCFileSysPath() {
        return gPCFileSysPath;
    }

    public void setgPCFileSysPath(String gPCFileSysPath) {
        this.gPCFileSysPath = gPCFileSysPath;
    }

    public String getgPCMachineExtensionNames() {
        return gPCMachineExtensionNames;
    }

    public void setgPCMachineExtensionNames(String gPCMachineExtensionNames) {
        this.gPCMachineExtensionNames = gPCMachineExtensionNames;
    }

    public String getIpsecName() {
        return ipsecName;
    }

    public void setIpsecName(String ipsecName) {
        this.ipsecName = ipsecName;
    }

    public String getIpsecID() {
        return ipsecID;
    }

    public void setIpsecID(String ipsecID) {
        this.ipsecID = ipsecID;
    }

    public String getIpsecDataType() {
        return ipsecDataType;
    }

    public void setIpsecDataType(String ipsecDataType) {
        this.ipsecDataType = ipsecDataType;
    }

    public String getIpsecData() {
        return ipsecData;
    }

    public void setIpsecData(String ipsecData) {
        this.ipsecData = ipsecData;
    }

    public String getIpsecISAKMPReference() {
        return ipsecISAKMPReference;
    }

    public void setIpsecISAKMPReference(String ipsecISAKMPReference) {
        this.ipsecISAKMPReference = ipsecISAKMPReference;
    }

    public String getIpsecNFAReference() {
        return ipsecNFAReference;
    }

    public void setIpsecNFAReference(String ipsecNFAReference) {
        this.ipsecNFAReference = ipsecNFAReference;
    }

    public String getIpsecOwnersReference() {
        return ipsecOwnersReference;
    }

    public void setIpsecOwnersReference(String ipsecOwnersReference) {
        this.ipsecOwnersReference = ipsecOwnersReference;
    }

    public String getIpsecNegotiationPolicyReference() {
        return ipsecNegotiationPolicyReference;
    }

    public void setIpsecNegotiationPolicyReference(String ipsecNegotiationPolicyReference) {
        this.ipsecNegotiationPolicyReference = ipsecNegotiationPolicyReference;
    }

    public String getIpsecFilterReference() {
        return ipsecFilterReference;
    }

    public void setIpsecFilterReference(String ipsecFilterReference) {
        this.ipsecFilterReference = ipsecFilterReference;
    }

    public String getiPSECNegotiationPolicyType() {
        return iPSECNegotiationPolicyType;
    }

    public void setiPSECNegotiationPolicyType(String iPSECNegotiationPolicyType) {
        this.iPSECNegotiationPolicyType = iPSECNegotiationPolicyType;
    }

    public String getiPSECNegotiationPolicyAction() {
        return iPSECNegotiationPolicyAction;
    }

    public void setiPSECNegotiationPolicyAction(String iPSECNegotiationPolicyAction) {
        this.iPSECNegotiationPolicyAction = iPSECNegotiationPolicyAction;
    }

    public String getRevision() {
        return revision;
    }

    public void setRevision(String revision) {
        this.revision = revision;
    }

    public String getMemberOf() {
        return memberOf;
    }

    public void setMemberOf(String memberOf) {
        this.memberOf = memberOf;
    }

    public String getUserAccountControl() {
        return userAccountControl;
    }

    public void setUserAccountControl(String userAccountControl) {
        this.userAccountControl = userAccountControl;
    }

    public String getBadPwdCount() {
        return badPwdCount;
    }

    public void setBadPwdCount(String badPwdCount) {
        this.badPwdCount = badPwdCount;
    }

    public String getCodePage() {
        return codePage;
    }

    public void setCodePage(String codePage) {
        this.codePage = codePage;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getBadPasswordTime() {
        return badPasswordTime;
    }

    public void setBadPasswordTime(String badPasswordTime) {
        this.badPasswordTime = badPasswordTime;
    }

    public String getLastLogoff() {
        return lastLogoff;
    }

    public void setLastLogoff(String lastLogoff) {
        this.lastLogoff = lastLogoff;
    }

    public String getLastLogon() {
        return lastLogon;
    }

    public void setLastLogon(String lastLogon) {
        this.lastLogon = lastLogon;
    }

    public String getLogonHours() {
        return logonHours;
    }

    public void setLogonHours(String logonHours) {
        this.logonHours = logonHours;
    }

    public String getPwdLastSet() {
        return pwdLastSet;
    }

    public void setPwdLastSet(String pwdLastSet) {
        this.pwdLastSet = pwdLastSet;
    }

    public String getPrimaryGroupID() {
        return primaryGroupID;
    }

    public void setPrimaryGroupID(String primaryGroupID) {
        this.primaryGroupID = primaryGroupID;
    }

    public String getAdminCount() {
        return adminCount;
    }

    public void setAdminCount(String adminCount) {
        this.adminCount = adminCount;
    }

    public String getAccountExpires() {
        return accountExpires;
    }

    public void setAccountExpires(String accountExpires) {
        this.accountExpires = accountExpires;
    }

    public String getLogonCount() {
        return logonCount;
    }

    public void setLogonCount(String logonCount) {
        this.logonCount = logonCount;
    }

    public String getsAMAccountName() {
        return sAMAccountName;
    }

    public void setsAMAccountName(String sAMAccountName) {
        this.sAMAccountName = sAMAccountName;
    }

    public String getsAMAccountType() {
        return sAMAccountType;
    }

    public void setsAMAccountType(String sAMAccountType) {
        this.sAMAccountType = sAMAccountType;
    }

    public String getLastLogonTimestamp() {
        return lastLogonTimestamp;
    }

    public void setLastLogonTimestamp(String lastLogonTimestamp) {
        this.lastLogonTimestamp = lastLogonTimestamp;
    }

    public String getMember() {
        return member;
    }

    public void setMember(String member) {
        this.member = member;
    }

    public String getGroupType() {
        return groupType;
    }

    public void setGroupType(String groupType) {
        this.groupType = groupType;
    }

    public String getSamDomainUpdates() {
        return samDomainUpdates;
    }

    public void setSamDomainUpdates(String samDomainUpdates) {
        this.samDomainUpdates = samDomainUpdates;
    }

    public String getLocalPolicyFlags() {
        return localPolicyFlags;
    }

    public void setLocalPolicyFlags(String localPolicyFlags) {
        this.localPolicyFlags = localPolicyFlags;
    }

    public String getOperatingSystem() {
        return operatingSystem;
    }

    public void setOperatingSystem(String operatingSystem) {
        this.operatingSystem = operatingSystem;
    }

    public String getOperatingSystemVersion() {
        return operatingSystemVersion;
    }

    public void setOperatingSystemVersion(String operatingSystemVersion) {
        this.operatingSystemVersion = operatingSystemVersion;
    }

    public String getOperatingSystemServicePack() {
        return operatingSystemServicePack;
    }

    public void setOperatingSystemServicePack(String operatingSystemServicePack) {
        this.operatingSystemServicePack = operatingSystemServicePack;
    }

    public String getServerReferenceBL() {
        return serverReferenceBL;
    }

    public void setServerReferenceBL(String serverReferenceBL) {
        this.serverReferenceBL = serverReferenceBL;
    }

    public String getdNSHostName() {
        return dNSHostName;
    }

    public void setdNSHostName(String dNSHostName) {
        this.dNSHostName = dNSHostName;
    }

    public String getrIDSetReferences() {
        return rIDSetReferences;
    }

    public void setrIDSetReferences(String rIDSetReferences) {
        this.rIDSetReferences = rIDSetReferences;
    }

    public String getServicePrincipalName() {
        return servicePrincipalName;
    }

    public void setServicePrincipalName(String servicePrincipalName) {
        this.servicePrincipalName = servicePrincipalName;
    }

    public String getMsDSSupportedEncryptionTypes() {
        return msDSSupportedEncryptionTypes;
    }

    public void setMsDSSupportedEncryptionTypes(String msDSSupportedEncryptionTypes) {
        this.msDSSupportedEncryptionTypes = msDSSupportedEncryptionTypes;
    }

    public String getMsDFSRComputerReferenceBL() {
        return msDFSRComputerReferenceBL;
    }

    public void setMsDFSRComputerReferenceBL(String msDFSRComputerReferenceBL) {
        this.msDFSRComputerReferenceBL = msDFSRComputerReferenceBL;
    }

    public String getrIDAvailablePool() {
        return rIDAvailablePool;
    }

    public void setrIDAvailablePool(String rIDAvailablePool) {
        this.rIDAvailablePool = rIDAvailablePool;
    }

    public String getrIDAllocationPool() {
        return rIDAllocationPool;
    }

    public void setrIDAllocationPool(String rIDAllocationPool) {
        this.rIDAllocationPool = rIDAllocationPool;
    }

    public String getrIDPreviousAllocationPool() {
        return rIDPreviousAllocationPool;
    }

    public void setrIDPreviousAllocationPool(String rIDPreviousAllocationPool) {
        this.rIDPreviousAllocationPool = rIDPreviousAllocationPool;
    }

    public String getrIDUsedPool() {
        return rIDUsedPool;
    }

    public void setrIDUsedPool(String rIDUsedPool) {
        this.rIDUsedPool = rIDUsedPool;
    }

    public String getrIDNextRID() {
        return rIDNextRID;
    }

    public void setrIDNextRID(String rIDNextRID) {
        this.rIDNextRID = rIDNextRID;
    }

    public String getMsDFSRFlags() {
        return msDFSRFlags;
    }

    public void setMsDFSRFlags(String msDFSRFlags) {
        this.msDFSRFlags = msDFSRFlags;
    }

    public String getMsDFSRReplicationGroupType() {
        return msDFSRReplicationGroupType;
    }

    public void setMsDFSRReplicationGroupType(String msDFSRReplicationGroupType) {
        this.msDFSRReplicationGroupType = msDFSRReplicationGroupType;
    }

    public String getMsDFSRFileFilter() {
        return msDFSRFileFilter;
    }

    public void setMsDFSRFileFilter(String msDFSRFileFilter) {
        this.msDFSRFileFilter = msDFSRFileFilter;
    }

    public String getMsDFSRDirectoryFilter() {
        return msDFSRDirectoryFilter;
    }

    public void setMsDFSRDirectoryFilter(String msDFSRDirectoryFilter) {
        this.msDFSRDirectoryFilter = msDFSRDirectoryFilter;
    }

    public String getServerReference() {
        return serverReference;
    }

    public void setServerReference(String serverReference) {
        this.serverReference = serverReference;
    }

    public String getMsDFSRComputerReference() {
        return msDFSRComputerReference;
    }

    public void setMsDFSRComputerReference(String msDFSRComputerReference) {
        this.msDFSRComputerReference = msDFSRComputerReference;
    }

    public String getMsDFSRMemberReferenceBL() {
        return msDFSRMemberReferenceBL;
    }

    public void setMsDFSRMemberReferenceBL(String msDFSRMemberReferenceBL) {
        this.msDFSRMemberReferenceBL = msDFSRMemberReferenceBL;
    }

    public String getMsDFSRVersion() {
        return msDFSRVersion;
    }

    public void setMsDFSRVersion(String msDFSRVersion) {
        this.msDFSRVersion = msDFSRVersion;
    }

    public String getMsDFSRReplicationGroupGuid() {
        return msDFSRReplicationGroupGuid;
    }

    public void setMsDFSRReplicationGroupGuid(String msDFSRReplicationGroupGuid) {
        this.msDFSRReplicationGroupGuid = msDFSRReplicationGroupGuid;
    }

    public String getMsDFSRMemberReference() {
        return msDFSRMemberReference;
    }

    public void setMsDFSRMemberReference(String msDFSRMemberReference) {
        this.msDFSRMemberReference = msDFSRMemberReference;
    }

    public String getMsDFSRRootPath() {
        return msDFSRRootPath;
    }

    public void setMsDFSRRootPath(String msDFSRRootPath) {
        this.msDFSRRootPath = msDFSRRootPath;
    }

    public String getMsDFSRStagingPath() {
        return msDFSRStagingPath;
    }

    public void setMsDFSRStagingPath(String msDFSRStagingPath) {
        this.msDFSRStagingPath = msDFSRStagingPath;
    }

    public String getMsDFSREnabled() {
        return msDFSREnabled;
    }

    public void setMsDFSREnabled(String msDFSREnabled) {
        this.msDFSREnabled = msDFSREnabled;
    }

    public String getMsDFSROptions() {
        return msDFSROptions;
    }

    public void setMsDFSROptions(String msDFSROptions) {
        this.msDFSROptions = msDFSROptions;
    }

    public String getMsDFSRContentSetGuid() {
        return msDFSRContentSetGuid;
    }

    public void setMsDFSRContentSetGuid(String msDFSRContentSetGuid) {
        this.msDFSRContentSetGuid = msDFSRContentSetGuid;
    }

    public String getMsDFSRReadOnly() {
        return msDFSRReadOnly;
    }

    public void setMsDFSRReadOnly(String msDFSRReadOnly) {
        this.msDFSRReadOnly = msDFSRReadOnly;
    }

    public String getLastSetTime() {
        return lastSetTime;
    }

    public void setLastSetTime(String lastSetTime) {
        this.lastSetTime = lastSetTime;
    }

    public String getPriorSetTime() {
        return priorSetTime;
    }

    public void setPriorSetTime(String priorSetTime) {
        this.priorSetTime = priorSetTime;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getInitials() {
        return initials;
    }

    public void setInitials(String initials) {
        this.initials = initials;
    }

    public String getUserPrincipalName() {
        return userPrincipalName;
    }

    public void setUserPrincipalName(String userPrincipalName) {
        this.userPrincipalName = userPrincipalName;
    }

   

}
