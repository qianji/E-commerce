package gov.guilin.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.sun.istack.internal.NotNull;

@Entity
@Table(name = "xc_supplier_member_reank_b")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "xx_supplier_member_reank_sequence")
public class SupplierCompanySale extends BaseEntity {

	/**
	 * @Daniel 
	 */
	private static final long serialVersionUID = -1493161555603850509L;
	/** 供应商编码*/
	private Supplier supplier ;
	/** 用户*/
	private Member member ;
	/** 会员等级 */
	private MemberRank memberRank;

	/**
	 * 获取供应商
	 * @return 供应商
	 */
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false)
	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}
	/**
	 * 获取用户
	 * @return 用户
	 */
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false)
	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}
	/**
	 * 获取用户等级
	 * @return 用户等级
	 */
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false)
	public MemberRank getMemberRank() {
		return memberRank;
	}

	public void setMemberRank(MemberRank memberRank) {
		this.memberRank = memberRank;
	}
	
}
